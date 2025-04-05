package com.example.visionbook.view.camerasBookNProfile

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator // Для индикатора загрузки
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog // Для блокирующего диалога загрузки
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.visionbook.models.QrCodeAnalyzer
import com.example.visionbook.models.api.ApiClient // Импорт нашего клиента API
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.visionbook.view.camerasBookNProfile.secondCameraScreens.CanceledPermissonScreen
import kotlinx.coroutines.launch // Для запуска корутин
import java.io.File
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visionbook.viewmodels.AuthVM
import java.util.concurrent.atomic.AtomicBoolean
import androidx.compose.runtime.rememberCoroutineScope // Уже должен быть
import androidx.lifecycle.viewmodel.compose.viewModel // Для получения AuthVM
import com.example.visionbook.models.dataclasses.QrValidationResponse // Импорт для типа ответа
import com.example.visionbook.view.navigation.GraphRoute // Импорт для навигации


@Composable
fun CameraBook(
    directory: File,
    navController: NavController,
    isCameraPermissionGranted: MutableState<Boolean>,
    authViewModel: AuthVM = viewModel() // Получаем AuthVM здесь
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    // ... (состояния isLoading, sessionCodeResult, showResultDialog, apiError, canSendApiRequest) ...
    var isLoading by remember { mutableStateOf(false) }
    // var sessionCodeResult by remember { mutableStateOf<String?>(null) } // Больше не нужен здесь напрямую
    var showResultDialog by remember { mutableStateOf(false) } // Возможно, тоже не нужен, если сразу уходим
    var apiError by remember { mutableStateOf<String?>(null) }
    val canSendApiRequest = remember { AtomicBoolean(true) }


    fun resetApiCallState() {
        isLoading = false
        // sessionCodeResult = null // Не нужен
        showResultDialog = false // Не нужен?
        apiError = null
        canSendApiRequest.set(true)
        Log.d("CameraBook", "API Call state reset. Ready for next scan.")
    }

    // Функция для запуска API запроса
    fun triggerQrValidation(qrToken: String) {
        if (canSendApiRequest.compareAndSet(true, false)) {
            Log.d("CameraBook", "Attempting to validate QR Token: $qrToken")
            isLoading = true
            apiError = null

            coroutineScope.launch {
                val result = ApiClient.validateQrCode(qrToken)
                // Загрузку можно убрать чуть позже, после навигации,
                // или оставить здесь, если навигация быстрая
                // isLoading = false // Убрал пока

                // VVV ВОТ ЗДЕСЬ VVV
                result.onSuccess { response: QrValidationResponse -> // Указываем тип лямбда-параметра
                    Log.d("CameraBook", "API Success. Response: $response")
                    val token = response.sessionToken
                    if (token != null) {
                        // --- НАЧАЛО ПРИМЕРНОГО КОДА ---
                        Log.d("CameraBook", "QR Validated. Token received. Fetching user info...")

                        // 1. Сохраняем токен и ЗАПУСКАЕМ получение данных пользователя В ФОНЕ
                        // AuthVM сама обработает загрузку и отображение в SettingsProfileScreen
                        authViewModel.setSessionTokenAndFetchUser(token)

                        // 2. Переходим на главный экран (или экран настроек)
                        // Пользователь увидит индикатор загрузки уже на следующем экране,
                        // пока fetchUserInfo выполняется.
                        navController.navigate(GraphRoute.MAIN) { // Или SettingsProfileScreen, или другой нужный роут
                            // Очищаем стек навигации до экрана сканирования QR (если нужно)
                            //popUpTo(navController.currentDestination?.id ?: 0) { inclusive = true }
                            popUpTo(GraphRoute.AUTH) { inclusive = true } // Пример: очистить все до графа аутентификации
                            launchSingleTop = true // Избегаем дублирования экрана
                        }
                        // --- КОНЕЦ ПРИМЕРНОГО КОДА ---
                        // isLoading = false // Можно установить false уже после начала навигации
                    } else {
                        // Ошибка: Успешный ответ API, но токен отсутствует
                        Log.e("CameraBook", "API Success but sessionToken is null!")
                        apiError = "Ошибка сервера: токен не получен."
                        isLoading = false // Загрузка завершена (неудачно)
                        canSendApiRequest.set(true) // Разрешаем новую попытку
                        Toast.makeText(context, apiError, Toast.LENGTH_LONG).show()
                    }

                }.onFailure { exception ->
                    Log.e("CameraBook", "API Failure", exception)
                    apiError = "Ошибка: ${exception.message ?: "Неизвестная сетевая ошибка"}"
                    isLoading = false // Загрузка завершена (неудачно)
                    canSendApiRequest.set(true) // Разрешаем новую попытку после ошибки
                    Toast.makeText(context, apiError, Toast.LENGTH_LONG).show()
                }
                // Если isLoading убран из onSuccess/onFailure, установите его здесь
                // чтобы индикатор скрылся в любом случае (кроме успешной навигации)
                if (!result.isSuccess || result.getOrNull()?.sessionToken == null) {
                    isLoading = false
                }
            }
        } else {
            Log.d("CameraBook", "API request is already in progress or waiting for reset.")
        }
    }

    // Создаем анализатор
    val qrCodeAnalyzer = remember {
        QrCodeAnalyzer { detectedQrValue ->
            triggerQrValidation(detectedQrValue)
        }
    }


    // --- Отображение UI (Box, BookCameraPreview, DrawQRFocusBox, индикатор загрузки) ---
    Box(modifier = Modifier.fillMaxSize()) {
        if (isCameraPermissionGranted.value) {
            BookCameraPreview(
                modifier = Modifier.fillMaxSize(),
                context = context,
                lifecycleOwner = lifecycleOwner,
                outputDirectory = directory,
                onMediaCaptured = { /* Обработка фото */ },
                qrCodeAnalyzer = qrCodeAnalyzer // Передаем анализатор
            )
            // ... другие элементы UI поверх камеры (BackButton, DrawQRFocusBox) ...
            Row (/* ... BackButton ... */) { BackButton(navController) }
            DrawQRFocusBox(modifier = Modifier.align(Alignment.Center))

        } else {
            // ... Экран отсутствия разрешений ...
            CanceledPermissonScreen()
        }

        // Индикатор загрузки
        if (isLoading) {
            Dialog(onDismissRequest = { /* Не закрывается */ }) {
                Box( /* ... */ ) { CircularProgressIndicator() }
            }
        }
    }
} // Конец Composable CameraBook

// ... Остальной код CameraBook.kt (BookCameraPreview, DrawQRFocusBox) ...


// --- Остальной код (BookCameraPreview, DrawQRFocusBox) остается без изменений ---
// Убедитесь, что BookCameraPreview принимает QrCodeAnalyzer и использует его
// (как в вашем исходном коде)

@Composable
fun BookCameraPreview(
    modifier: Modifier = Modifier,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit,
    qrCodeAnalyzer: QrCodeAnalyzer // Принимаем анализатор
) {
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview: Preview? by remember { mutableStateOf(null) }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraProvider = remember(cameraProviderFuture) { mutableStateOf<ProcessCameraProvider?>(null) }
    // Используем заднюю камеру по умолчанию, как и раньше
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }

    LaunchedEffect(cameraProviderFuture) {
        try {
            cameraProvider.value = cameraProviderFuture.get()
        } catch (e: ExecutionException) {
            Log.e("BookCameraPreview", "Error getting camera provider", e)
        } catch (e: InterruptedException) {
            Log.e("BookCameraPreview", "Error getting camera provider", e)
        }
    }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }

            cameraProviderFuture.addListener({
                val provider = cameraProvider.value ?: run {
                    Log.e("BookCameraPreview", "CameraProvider is null on listener execution")
                    return@addListener
                }

                try {
                    provider.unbindAll()

                    preview = Preview.Builder()
                        .build()
                        .also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                    // ImageCapture все еще можно создать, если он нужен для других целей
                    imageCapture = ImageCapture.Builder()
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .setTargetRotation(previewView.display.rotation)
                        .build()
                        .apply {
                            // Передаем наш qrCodeAnalyzer и executor
                            setAnalyzer(cameraExecutor, qrCodeAnalyzer)
                        }

                    provider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis,
                        // imageCapture // Раскомментируйте, если нужен захват фото
                    )
                    Log.d("BookCameraPreview", "Camera use cases bound successfully with QR Analyzer")

                } catch (exc: Exception) {
                    Log.e("BookCameraPreview", "Use case binding failed", exc)
                    Toast.makeText(context, "Failed to start camera: ${exc.message}", Toast.LENGTH_LONG).show()
                }
            }, ContextCompat.getMainExecutor(context))


            previewView
        },
        modifier = modifier
    )

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }
}

// DrawQRFocusBox остается таким же
@Composable
fun DrawQRFocusBox(modifier: Modifier = Modifier, color: Color = Color.Green, strokeWidth: Float = 8f) {
    // ... (код без изменений)
    val boxSize = 250.dp // Размер рамки
    val cornerLength = 30.dp // Длина уголков

    Canvas(modifier = modifier.size(boxSize)) {
        val halfStroke = strokeWidth / 2f
        val maxDim = size.minDimension
        val rectSize = maxDim * 0.9f // Размер внутреннего прямоугольника (немного меньше Canvas)
        val topLeft = Offset((size.width - rectSize) / 2f, (size.height - rectSize) / 2f)
        val drawSize = Size(rectSize, rectSize)
        val cornerLenPx = cornerLength.toPx()

        // Рисуем 4 уголка
        // Top-left
        drawLine(color, Offset(topLeft.x - halfStroke, topLeft.y + cornerLenPx), Offset(topLeft.x - halfStroke, topLeft.y - halfStroke), strokeWidth)
        drawLine(color, Offset(topLeft.x + cornerLenPx, topLeft.y - halfStroke), Offset(topLeft.x - halfStroke, topLeft.y - halfStroke), strokeWidth)
        // Top-right
        drawLine(color, Offset(topLeft.x + rectSize + halfStroke, topLeft.y + cornerLenPx), Offset(topLeft.x + rectSize + halfStroke, topLeft.y - halfStroke), strokeWidth)
        drawLine(color, Offset(topLeft.x + rectSize - cornerLenPx, topLeft.y - halfStroke), Offset(topLeft.x + rectSize + halfStroke, topLeft.y - halfStroke), strokeWidth)
        // Bottom-left
        drawLine(color, Offset(topLeft.x - halfStroke, topLeft.y + rectSize - cornerLenPx), Offset(topLeft.x - halfStroke, topLeft.y + rectSize + halfStroke), strokeWidth)
        drawLine(color, Offset(topLeft.x + cornerLenPx, topLeft.y + rectSize + halfStroke), Offset(topLeft.x - halfStroke, topLeft.y + rectSize + halfStroke), strokeWidth)
        // Bottom-right
        drawLine(color, Offset(topLeft.x + rectSize + halfStroke, topLeft.y + rectSize - cornerLenPx), Offset(topLeft.x + rectSize + halfStroke, topLeft.y + rectSize + halfStroke), strokeWidth)
        drawLine(color, Offset(topLeft.x + rectSize - cornerLenPx, topLeft.y + rectSize + halfStroke), Offset(topLeft.x + rectSize + halfStroke, topLeft.y + rectSize + halfStroke), strokeWidth)
    }
}


