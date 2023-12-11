package org.shop.yogizogi_android.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.remote.datasource.UserRemoteData
import org.shop.yogizogi_android.data.model.remote.request.auth.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokFeedResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.TasteRegisterResDTO
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteData: UserRemoteData,
    private val userDataStore: UserDataStore
) :
    UserRepository {
    override suspend fun createProfile(
        header: String,
        body: ProfileCreateReqDTO
    ): Flow<Resource<ProfileCreateResDTO>> {
        return flow {
            emit(userRemoteData.putUserProfile(header, body))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun createTaste(
        header: String,
        body: TasteRegisterReqDTO
    ): Flow<Resource<TasteRegisterResDTO>> {
        return flow {
            emit(userRemoteData.postMeokProfile(header, body))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserMap(
        header: String,
        userId: String
    ): Flow<Resource<List<MeokMapResDTO>>> {
        return flow {
            emit(userRemoteData.getUserMap(header, userId))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserFeed(
        header: String,
        userId: String
    ): Flow<Resource<MeokFeedResDTO>> {
        return flow {
            emit(userRemoteData.getUserFeed(header, userId))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserData(): Flow<UserPreference> {
        return flow {
            Log.d("UserRepository - getUser", userDataStore.getUserPreference().first().toString())
            emit(userDataStore.getUserPreference().first())
        }.flowOn(Dispatchers.IO)
    }
}