package com.example.myapplication.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/") // базовый URL  API
        .addConverterFactory(GsonConverterFactory.create()) // использование Gson для работы с JSON
        .build()
}