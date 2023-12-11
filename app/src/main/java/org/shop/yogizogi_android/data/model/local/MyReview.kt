package org.shop.yogizogi_android.data.model.local

data class MyReview(
    val id: Long,
    val content: String?,
    val recommendationStatus: Boolean,
    val images: List<String>
)
