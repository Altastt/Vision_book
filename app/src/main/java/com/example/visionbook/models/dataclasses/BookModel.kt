package com.example.visionbook.models.dataclasses

data class GetBooksModel(
    val status: String,
    val book: List<BooksModel>
)

data class BooksModel (
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

data class BookModelToShare(
    val title: String,
    val author: String,
    val genre: String
)

data class BookToShareModelResponse (
    val status: String,
    val idBook: Int
)



data class BookToHistory(
    val idBook: Int
)

data class BookToHistoryResponse(
    val status: String
)