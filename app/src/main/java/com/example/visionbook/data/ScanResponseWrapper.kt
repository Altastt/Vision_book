package com.example.visionbook.data

data class ScanResponseWrapper(
    val status: String?,
    val bookId: Int?,
    val recognizedData: RecognizedData?
)
