package com.example.visionbook.data

sealed class ScanResult {
    data class Found(val bookId: Int) : ScanResult()
    data class NotFound(val recognizedData: RecognizedData?) : ScanResult()
    object OcrFailed : ScanResult() // Используем object для статуса без доп. данных
}
