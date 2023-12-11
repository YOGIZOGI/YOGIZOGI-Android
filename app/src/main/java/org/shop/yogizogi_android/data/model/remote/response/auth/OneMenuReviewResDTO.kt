package org.shop.yogizogi_android.data.model.remote.response.auth

data class OneMenuReviewResDTO(
    val menuReviewId: Long,
    val nickname: String,
    val meokProfile: MeokProfileVO,
    val meokReview: MenuReviewVO
)

data class MeokProfileVO(
    val intensity: Intensity,
    val preference: Preference
)