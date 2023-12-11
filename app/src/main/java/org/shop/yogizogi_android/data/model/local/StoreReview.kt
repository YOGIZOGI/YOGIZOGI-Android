package org.shop.yogizogi_android.data.model.local

import java.io.Serializable

data class StoreReview(
    val nickname: String,
    val imgList: List<String>,
    val isVerified: Boolean,
    val isFavorite: Boolean,
    val hotWell: Int,
    val hotPrefer: Int,
    val sweetWell: Int,
    val sweetPrefer: Int,
    val saltyWell: Int,
    val saltyPrefer: Int,
    val reviewContent: String?
) : Serializable
