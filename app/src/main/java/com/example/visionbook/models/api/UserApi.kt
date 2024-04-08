package com.example.visionbook.models.api

import com.example.visionbook.models.dataclasses.AddUserModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {
    @Headers("Content-Type: application/json")
    @POST("user/addUser")
    suspend fun changeUser(@Header("Authorization") token: String, @Body addUserModel: AddUserModel)

}