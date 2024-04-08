package com.example.visionbook.models.api

import com.example.visionbook.models.dataclasses.BookIdModel
import com.example.visionbook.models.dataclasses.BookModel
import com.example.visionbook.models.dataclasses.BookToShareModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface BooksApi {
    // @Headers("Content-Type: application/json")  НАДА
    @GET("book")
    suspend fun getBooks(
        @Header("Authorization") token: String,
        @Query("amount_books") amount: Int
    ): List<BookModel>

    @Headers("Content-Type: application/json")
    @POST("book/add")
    suspend fun addBookToSharedList(
        @Header("Authorization") token: String,
        @Body bookToShare: BookToShareModel
    ): Int

    @Headers("Content-Type: application/json")
    @POST("book/history")
    suspend fun addBookToHistory(@Header("Authorization") token: String, @Body idBook: BookIdModel)

}