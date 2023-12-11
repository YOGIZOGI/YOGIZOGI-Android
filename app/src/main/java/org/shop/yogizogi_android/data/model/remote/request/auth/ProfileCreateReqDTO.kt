package org.shop.yogizogi_android.data.model.remote.request.auth

data class ProfileCreateReqDTO(
    val nickname: String,
    val imageUrl: String,
    val introduction: String
)
