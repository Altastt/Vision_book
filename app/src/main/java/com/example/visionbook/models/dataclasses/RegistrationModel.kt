package com.example.visionbook.models.dataclasses

data class RegistrationModel(
    val email: String,
    val password: String
)

data class AuthModel(
    val status: String,
    val token: String
)

data class RegModel(
    val status: String
)