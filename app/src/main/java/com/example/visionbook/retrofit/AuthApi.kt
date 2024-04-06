package com.example.visionbook.retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun auth(@Body authRequest: AuthRequest): Auth  // передача email и password, принимаем id и token
}