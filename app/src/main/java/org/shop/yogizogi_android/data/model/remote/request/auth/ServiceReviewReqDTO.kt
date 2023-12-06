package org.shop.yogizogi_android.data.model.remote.request.auth

data class ServiceReviewReqDTO(
    val reviewId: String,
    val rating: Double,
    val yogiMoods: List<String>
)
