package com.example.visionbook.retrofit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory








fun  TestAddBook() {

    val interceptor = HttpLoggingInterceptor() // для вывода логов
    interceptor.level = HttpLoggingInterceptor.Level.BODY // перехват тела
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    val retrofit = Retrofit.Builder()        // Инициализация библиотеки ретрофит
        .baseUrl("http://localhost:3000").client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookApi = retrofit.create(BookApi::class.java) // создание класса из JSON, можно уже вызывать bookApi.addBook() (Определено в интерфейсе BookApi)


    CoroutineScope(Dispatchers.IO).launch{
        val addBook = bookApi.addBook() // если метод addBook что-то возвращает, то можем записать в переменную и исопользовать для вью
    }
}

