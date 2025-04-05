package com.example.visionbook.models

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.atomic.AtomicBoolean

class QrCodeAnalyzer(
    private val onQrCodeDetected: (String) -> Unit // Переименовали для ясности
) : ImageAnalysis.Analyzer {

    // Флаг, чтобы избежать мгновенной повторной отправки одного и того же кадра
    // на обработку ВЫШЕ (в CameraBook). Сам ML Kit может обрабатывать быстро.
    private var canProcessFrame = AtomicBoolean(true)

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        // Если предыдущий кадр еще не отработал (или мы не разрешили новый анализ), выходим
        if (!canProcessFrame.get()) {
            imageProxy.close()
            return
        }

        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            // Запрещаем обработку следующего кадра, пока этот не завершится
            canProcessFrame.set(false)

            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        barcodes[0].rawValue?.let { qrCodeValue ->
                            Log.d("QrCodeAnalyzer", "QR Code detected: $qrCodeValue")
                            // Просто передаем результат наверх
                            onQrCodeDetected(qrCodeValue)
                            // Не управляем isScanning здесь, пусть CameraBook решает,
                            // когда разрешить НОВЫЙ API-запрос через resetApiCallState()
                        }
                    }
                    // Вне зависимости от результата, разрешаем обработку СЛЕДУЮЩЕГО КАДРА
                    // (но не обязательно новый API вызов)
                    canProcessFrame.set(true)
                }
                .addOnFailureListener { e ->
                    Log.e("QrCodeAnalyzer", "Barcode scanning failed", e)
                    // Разрешаем обработку следующего кадра в случае ошибки ML Kit
                    canProcessFrame.set(true)
                }
                .addOnCompleteListener {
                    // ВАЖНО: Закрываем ImageProxy
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
            // Если mediaImage null, разрешаем следующий кадр
            canProcessFrame.set(true)
        }
    }

    // Этот метод больше не нужен здесь, состояние управляется в CameraBook
    // fun resetScanning() { ... }
}
