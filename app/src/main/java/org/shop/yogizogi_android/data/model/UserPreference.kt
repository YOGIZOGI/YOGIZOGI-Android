package org.shop.yogizogi_android.data.model

import org.shop.yogizogi_android.utils.ActiveState
import org.shop.yogizogi_android.utils.ActiveState.INACTIVE
import kotlinx.serialization.Serializable

@Serializable
data class UserPreference(
    val id: String,
    val firstLogInStatus: ActiveState,
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun getDefaultInstance() = UserPreference(
            id = "",
            firstLogInStatus = INACTIVE,
            accessToken = "",
            refreshToken = ""
        )
    }
}