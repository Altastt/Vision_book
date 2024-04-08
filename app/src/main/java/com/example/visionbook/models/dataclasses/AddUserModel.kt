package com.example.visionbook.models.dataclasses

data class AddUserModel(
    val user: UserModel,
    val image: String
)

data class UserModel (
    val nickname: String
)