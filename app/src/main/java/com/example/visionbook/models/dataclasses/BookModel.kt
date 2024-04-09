package com.example.visionbook.models.dataclasses

data class BookModel(
    val status: String,
    val book: List<Book>,
)
data class Book (
    val idBook: Int,
    val title: String,
    val author: String,
    val genre: String,
    val urlImg: String
)



data class BookToShareModel (
    val book: BookModelToShare,
    val image: String
)
data class BookModelToShare (
    val title: String,
    val author: String,
    val genre: String,
)
data class BookToShareModelResponse(
    val status: String,
    val idBook: Int
)




data class BooktoHistoryModel(
    val idBook: Int
)
data class BooktoHistoryModelResponse(
    val status: String
)