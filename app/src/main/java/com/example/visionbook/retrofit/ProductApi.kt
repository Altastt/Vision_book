package com.example.visionbook.retrofit
import retrofit2.http.GET

interface ProductApi {
    @GET("products/1")
    fun getProductsById() {

    }
}