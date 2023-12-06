package org.shop.yogizogi_android.data.model.remote.response.auth

data class MeokFeedResDTO(
    val userProfile: Profile,
    val menuReviews: List<MenuReviewVO>
)

data class Profile(
    val nickname: String,
    val imageUrl: String,
    val introduction: String
)

data class MenuReviewVO(
    val id: Long,
    val content: String,
    val recommendationStatus: String,
    val images: List<String>
)
