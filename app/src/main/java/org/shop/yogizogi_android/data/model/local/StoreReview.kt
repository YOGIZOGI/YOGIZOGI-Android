package org.shop.yogizogi_android.data.model.local

data class StoreReview(
    val nickname: String,
    val isVerified: Boolean,
    val isFavorite: Boolean,
    val hotWell: Int,
    val hotPrefer: Int,
    val sweetWell: Int,
    val sweetPrefer: Int,
    val saltyWell: Int,
    val saltyPrefer: Int,
    val reviewContent: String
)
