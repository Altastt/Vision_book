package com.example.visionbook.models.api

import android.net.Uri
import android.util.Log
import com.example.visionbook.data.ApiErrorResponse
import com.example.visionbook.data.BookDetailsResponse
import com.example.visionbook.data.ScanResponseWrapper
import com.example.visionbook.data.ScanResult
import com.example.visionbook.data.UserInfoResponse
import com.example.visionbook.models.* // Импорт всех наших моделей
import com.example.visionbook.models.dataclasses.QrValidationRequest
import com.example.visionbook.models.dataclasses.QrValidationResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.* // Для работы с файлами и потоками
import java.net.HttpURLConnection
import java.net.URL
import java.util.* // Для UUID
import com.example.visionbook.data.*
import com.google.gson.JsonSyntaxExceptio


object ApiClient {

    // ... (BASE_URL, gson, ApiException - без изменений) ...
    private const val BASE_URL = "http://10.0.2.2:8080"
    private val gson = Gson()
    class ApiException( /* ... */ ) : IOException( /* ... */ )


    // === ОСНОВНЫЕ МЕТОДЫ API ===

    suspend fun validateQrCode(qrToken: String): Result<QrValidationResponse> {
        return networkRequest(
            endpoint = "/api/v1/auth/validate-qr",
            method = "POST",
            body = gson.toJson(QrValidationRequest(qrToken))
        ) { responseBody ->
            // --- ИСПРАВЛЕНИЕ ЗДЕСЬ ---
            gson.fromJson<QrValidationResponse>(responseBody, QrValidationResponse::class.java)
        }
    }

    suspend fun getUserInfo(token: String): Result<UserInfoResponse> {
        return networkRequest(
            endpoint = "/api/v1/auth/user",
            method = "GET",
            authToken = token
        ) { responseBody ->
            // --- ИСПРАВЛЕНИЕ ЗДЕСЬ ---
            gson.fromJson<UserInfoResponse>(responseBody, UserInfoResponse::class.java)
        }
    }

    suspend fun getBookDetails(bookId: Int, token: String): Result<BookDetailsResponse> {
        return networkRequest(
            endpoint = "/api/v1/books/$bookId",
            method = "GET",
            authToken = token
        ) { responseBody ->
            // --- ИСПРАВЛЕНИЕ ЗДЕСЬ ---
            gson.fromJson<BookDetailsResponse>(responseBody, BookDetailsResponse::class.java)
        }
    }

    suspend fun scanBook(imageUri: Uri, token: String, context: Context): Result<ScanResult> {
        return withContext(Dispatchers.IO) {
            // ... (логика multipart запроса без изменений) ...
            // Внутри блока try/catch, где обрабатывается успешный ответ (responseCode == HTTP_OK):
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val responseBody = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("ApiClient", "ScanBook Response Body: $responseBody")
                try {
                    // --- ИСПРАВЛЕНИЕ ЗДЕСЬ (для парсинга обертки) ---
                    val wrapper = gson.fromJson<ScanResponseWrapper>(responseBody, ScanResponseWrapper::class.java)
                    // ... (остальная логика when по wrapper.status) ...
                    when (wrapper.status) {
                        "found" -> Result.success(ScanResult.Found(wrapper.bookId ?: -1))
                        "not_found" -> Result.success(ScanResult.NotFound(wrapper.recognizedData))
                        "ocr_failed" -> Result.success(ScanResult.OcrFailed)
                        else -> {
                            Log.e("ApiClient", "Unknown status in scan response: ${wrapper.status}")
                            Result.failure(Exception("Unknown status in scan response: ${wrapper.status}"))
                        }
                    }
                } catch (e: JsonSyntaxException) {
                    Log.e("ApiClient", "Failed to parse scan response JSON", e)
                    Result.failure(Exception("Failed to parse scan response JSON", e))
                }
            } else {
                // ... (обработка ошибки HTTP без изменений) ...
                val errorBody = connection.errorStream?.bufferedReader()?.use { it.readText() } ?: "No error body"
                Log.e("ApiClient", "ScanBook HTTP error: $responseCode. Body: $errorBody")
                Result.failure(parseApiError(responseCode, errorBody))
            }
            // ... (конец блока try/catch) ...
        }
    }

    // ... (connectWebSocket без изменений - требует OkHttp/Ktor) ...
    fun connectWebSocket( /* ... */ ) { /* ... */ }


    // === ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ===

    private suspend inline fun <reified T> networkRequest(
        endpoint: String,
        method: String,
        body: String? = null,
        authToken: String? = null,
        crossinline parser: (String) -> T
    ): Result<T> {
        // --- Внутренняя логика networkRequest остается БЕЗ ИЗМЕНЕНИЙ ---
        // Она вызывает лямбду 'parser', которая теперь явно указывает тип в fromJson
        return withContext(Dispatchers.IO) {
            // ... (весь код networkRequest) ...
            // В блоке if (responseCode == HttpURLConnection.HTTP_OK) { ... try { ... } }
            // val parsedData = parser(responseBody) // Этот вызов остается прежним
            // Result.success(parsedData)
            // ...
        }
    }

    private fun parseApiError(responseCode: Int, errorBody: String): ApiException {
        // --- ИСПРАВЛЕНИЕ ЗДЕСЬ (на всякий случай) ---
        if (errorBody.isBlank()) {
            return ApiException(responseCode, "HTTP_$responseCode", "HTTP error $responseCode with empty body")
        }
        return try {
            val apiError = gson.fromJson<ApiErrorResponse>(errorBody, ApiErrorResponse::class.java) // Указываем тип
            ApiException(
                httpCode = responseCode,
                apiCode = apiError.code ?: "UNKNOWN_CODE",
                message = apiError.message ?: errorBody
            )
        } catch (e: JsonSyntaxException) {
            Log.w("ApiClient", "Could not parse error JSON: $errorBody", e)
            ApiException(responseCode, "HTTP_$responseCode", "HTTP error $responseCode: $errorBody")
        } catch (e: Exception) {
            Log.w("ApiClient", "Exception parsing error body: $errorBody", e)
            ApiException(responseCode, "PARSE_ERROR", "HTTP error $responseCode. Failed to parse error body.")
        }
    }
} // Конец object ApiClient
