package org.shop.yogizogi_android.data

import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.local.MyReview
import org.shop.yogizogi_android.data.model.remote.response.auth.MenuReviewVO
import org.shop.yogizogi_android.data.model.remote.response.unauth.LogInResDTO
import org.shop.yogizogi_android.utils.ActiveState

fun LogInResDTO.toPreference() = UserPreference(
    id = id,
    firstLogInStatus = if (this.firstLogInStatus == "ACTIVE") ActiveState.ACTIVE else ActiveState.INACTIVE,
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun MenuReviewVO.toMyReview() = MyReview(
    id = id,
    content = content,
    recommendationStatus = this.recommendationStatus == "RECOMMEND",
    images = images
)