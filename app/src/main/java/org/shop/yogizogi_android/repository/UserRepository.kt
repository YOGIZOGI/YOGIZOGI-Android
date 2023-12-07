package org.shop.yogizogi_android.repository

import kotlinx.coroutines.flow.Flow
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.remote.request.auth.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokFeedResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.TasteRegisterResDTO

interface UserRepository {
    suspend fun createProfile(
        header: String,
        body: ProfileCreateReqDTO
    ): Flow<Resource<ProfileCreateResDTO>>

    suspend fun createTaste(
        header: String,
        body: TasteRegisterReqDTO
    ): Flow<Resource<TasteRegisterResDTO>>

    suspend fun getUserMap(
        header: String,
        userId: String
    ): Flow<Resource<List<MeokMapResDTO>>>

    suspend fun getUserFeed(
        header: String,
        userId: String
    ): Flow<Resource<MeokFeedResDTO>>

    suspend fun getUserData(): Flow<UserPreference>
}