package com.example.myapplication.models

import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET("endpoint") // Аннотация указывает, что это GET-запрос и указывает относительный URL
    fun getData(): Call<Metadata> { // Определение метода для выполнения GET-запроса

    }
}