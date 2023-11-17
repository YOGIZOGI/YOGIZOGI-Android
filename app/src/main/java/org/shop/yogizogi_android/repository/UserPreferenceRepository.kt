package org.shop.yogizogi_android.repository

import android.util.Log
import androidx.datastore.core.DataStore
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.remote.response.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.toPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferenceRepository @Inject constructor(private val userPreferenceDataStore: DataStore<UserPreference>) {
    fun getUserPreference() = userPreferenceDataStore.data

    suspend fun updateUserPreference(userInfo: LogInResDTO) = kotlin.runCatching {
        userPreferenceDataStore.updateData { userPreference ->
            userPreference.copy(
                id = userInfo.toPreference().id,
                firstLogInStatus = userInfo.toPreference().firstLogInStatus,
                accessToken = userInfo.toPreference().accessToken,
                refreshToken = userInfo.toPreference().refreshToken
            )
        }
        Log.d("UserPreferenceRepo - updatePreference", userPreferenceDataStore.data.toString())
    }

    suspend fun clearUserPreference() = kotlin.runCatching {
        userPreferenceDataStore.updateData {
            UserPreference.getDefaultInstance()
        }
    }
}