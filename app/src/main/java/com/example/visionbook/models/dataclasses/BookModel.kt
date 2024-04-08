package com.example.visionbook.models.dataclasses

data class BookModel(
    val title: String,
    val author: String,
    val genre: String
)

data class BookToShareModel (
    val book: BookModel,
    val image: String
)

data class BookIdModel (
    val idBook: Int
)
