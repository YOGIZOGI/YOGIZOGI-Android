package org.shop.yogizogi_android.repository

import androidx.datastore.core.DataStore
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.remote.response.unauth.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.toPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStore @Inject constructor(private val userDataStore: DataStore<UserPreference>) {
    fun getUserPreference() = userDataStore.data

    suspend fun updateUserPreference(userInfo: LogInResDTO) = kotlin.runCatching {
        userDataStore.updateData { userPreference ->
            userPreference.copy(
                id = userInfo.toPreference().id,
                firstLogInStatus = userInfo.toPreference().firstLogInStatus,
                accessToken = userInfo.toPreference().accessToken,
                refreshToken = userInfo.toPreference().refreshToken
            )
        }
    }

    suspend fun clearUserPreference() = kotlin.runCatching {
        userDataStore.updateData {
            UserPreference.getDefaultInstance()
        }
    }
}