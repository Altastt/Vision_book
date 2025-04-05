package com.example.visionbook.data

data class BookDetailsResponse(
    val id: Int?,
    val title: String?,
    val author: String?,
    val isbn: String?,
    val description: String?,
    val coverUrl: String?
    // Добавь другие поля книги
)
