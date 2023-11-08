package org.shop.yogizogi_android.data.model.remote.response

data class CommonSuccessRes<T>(
    val timestamp: String,
    val trackingId: String,
    val status: String,
    val data: T
)

data class CommonFailRes(
    val timestamp: String,
    val trackingId: String,
    val status: String,
    val message: String
)
