package org.shop.yogizogi_android.repository

import kotlinx.coroutines.flow.Flow
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO

interface UserRepository {
    suspend fun createProfile(body: ProfileCreateReqDTO): Flow<Resource<ProfileCreateResDTO>>
    suspend fun getUserData(): Flow<UserPreference>
}