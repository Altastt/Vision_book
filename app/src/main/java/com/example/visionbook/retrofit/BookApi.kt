package com.example.visionbook.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {
    @GET("book/add")
    suspend fun addBook() {

    }

    @GET("book/{id}")
    suspend fun getBookById(@Path("id") id: Int): Book // Реализация получения объекта по id
}