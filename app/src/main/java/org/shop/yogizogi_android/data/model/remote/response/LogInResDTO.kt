package org.shop.yogizogi_android.data.model.remote.response

import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.utils.ActiveState

data class LogInResDTO(
    val id: String,
    val firstLogInStatus: String,
    val accessToken: String,
    val refreshToken: String
)

fun LogInResDTO.toPreference() = UserPreference(
    id = id,
    firstLogInStatus = if (this.firstLogInStatus == "ACTIVE") ActiveState.ACTIVE else ActiveState.INACTIVE,
    accessToken = accessToken,
    refreshToken = refreshToken
)