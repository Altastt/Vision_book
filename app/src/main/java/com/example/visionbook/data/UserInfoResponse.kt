package com.example.visionbook.data

data class UserInfoResponse(
    val id: String?, // Или Int, если ID числовой
    val sessionCodeForDisplay: String?,
    val role: String?,
    val name: String?,
    val email: String?,
    val departmentId: String?,
    val departmentName: String?
)

