package org.shop.yogizogi_android.data.model.remote.response.unauth

data class LogInResDTO(
    val id: String,
    val firstLogInStatus: String,
    val accessToken: String,
    val refreshToken: String
)