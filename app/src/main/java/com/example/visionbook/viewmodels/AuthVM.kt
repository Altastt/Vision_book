package com.example.visionbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visionbook.data.UserInfoResponse // Импорт data класса
import com.example.visionbook.models.api.ApiClient // Импорт API клиента
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log // Для логирования

class AuthVM : ViewModel() {

    // Храним токен (предполагаем, что он уже здесь сохраняется после validateQrCode)
    private val _sessionToken = MutableStateFlow<String?>(null)
    val sessionToken: StateFlow<String?> = _sessionToken.asStateFlow()

    // --- НОВОЕ: Состояние для хранения информации о пользователе ---
    private val _userInfo = MutableStateFlow<UserInfoResponse?>(null)
    val userInfo: StateFlow<UserInfoResponse?> = _userInfo.asStateFlow()

    // --- НОВОЕ: Состояние загрузки информации о пользователе ---
    private val _isLoadingUserInfo = MutableStateFlow(false)
    val isLoadingUserInfo: StateFlow<Boolean> = _isLoadingUserInfo.asStateFlow()

    // --- НОВОЕ: Состояние ошибки при загрузке информации о пользователе ---
    private val _userInfoError = MutableStateFlow<String?>(null)
    val userInfoError: StateFlow<String?> = _userInfoError.asStateFlow()

    // Вызывается после успешной валидации QR-кода
    fun setSessionTokenAndFetchUser(token: String) {
        _sessionToken.value = token
        // Сразу после получения токена запрашиваем информацию о пользователе
        fetchUserInfo(token)
    }

    // --- НОВАЯ ФУНКЦИЯ: Запрос информации о пользователе ---
    fun fetchUserInfo(token: String?) {
        // Если токена нет, выходим
        if (token == null) {
            _userInfoError.value = "Невозможно получить информацию: токен отсутствует."
            Log.e("AuthVM", "fetchUserInfo called with null token")
            return
        }

        viewModelScope.launch {
            _isLoadingUserInfo.value = true
            _userInfoError.value = null // Сбрасываем предыдущую ошибку
            Log.d("AuthVM", "Fetching user info with token: Bearer ***") // Не логируем сам токен

            try {
                val result = ApiClient.getUserInfo(token) // Вызываем метод API

                result.onSuccess { fetchedUserInfo ->
                    Log.d("AuthVM", "User info fetched successfully: $fetchedUserInfo")
                    _userInfo.value = fetchedUserInfo // Сохраняем результат в StateFlow
                    // Теперь departmentId и departmentName доступны через _userInfo.value
                }.onFailure { exception ->
                    Log.e("AuthVM", "Failed to fetch user info", exception)
                    // Обрабатываем ошибку (например, показываем сообщение)
                    val errorMessage = if (exception is ApiClient.ApiException) {
                        "Ошибка API (${exception.apiCode ?: exception.httpCode}): ${exception.message}"
                    } else {
                        "Ошибка сети: ${exception.message}"
                    }
                    _userInfoError.value = errorMessage
                    _userInfo.value = null // Сбрасываем старые данные при ошибке
                }
            } catch (e: Exception) {
                // Ловим другие возможные исключения
                Log.e("AuthVM", "Unexpected error during fetchUserInfo", e)
                _userInfoError.value = "Неожиданная ошибка: ${e.message}"
                _userInfo.value = null
            } finally {
                _isLoadingUserInfo.value = false // Загрузка завершена (успех или ошибка)
            }
        }
    }

    // Функция для выхода из системы (очистка данных)
    fun logout() {
        _sessionToken.value = null
        _userInfo.value = null
        _userInfoError.value = null
        _isLoadingUserInfo.value = false
        // Здесь может быть дополнительная логика (очистка SharedPreferences и т.д.)
        Log.d("AuthVM", "User logged out, session data cleared.")
    }


    // --- НОВЫЙ МЕТОД (Пример): Получение ID департамента ---
    // Это просто геттер для удобства, можно обращаться и напрямую к userInfo.value?.departmentId
    fun getCurrentDepartmentId(): String? {
        return _userInfo.value?.departmentId
    }

    // --- НОВЫЙ МЕТОД (Пример): Получение имени департамента ---
    fun getCurrentDepartmentName(): String? {
        return _userInfo.value?.departmentName
    }
}
