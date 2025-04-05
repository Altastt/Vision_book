package com.example.visionbook.models.dataclasses

data class QrValidationRequest(val qrToken: String)

data class QrValidationResponse(
    val sessionCode: String?,
    val sessionToken: String?
)
