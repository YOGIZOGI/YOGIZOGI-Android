package org.shop.yogizogi_android.data.model.remote.request.auth

data class MenuReviewReqDTO(
    val reviewId: String,
    val menuId: Long,
    val content: String,
    val recommend: String,
    val imageUrl: List<String>
)
