package org.shop.yogizogi_android.data.model.remote.response.auth

data class SpecificMenuReviewResDTO(
    val menuId: Long,
    val menuReviews: List<OneMenuReviewResDTO>
)
