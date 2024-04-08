package com.example.visionbook.models.api

import com.example.visionbook.models.dataclasses.RegistrationModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("auth/register")
    suspend fun userRegistration(@Body registration: RegistrationModel)

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun userLogin(@Body registration: RegistrationModel): String
}