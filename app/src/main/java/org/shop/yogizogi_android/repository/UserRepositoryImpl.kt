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
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteData: UserRemoteData,
    private val userDataStore: UserDataStore
) :
    UserRepository {
    override suspend fun createProfile(body: ProfileCreateReqDTO): Flow<Resource<ProfileCreateResDTO>> {
        return flow {
            emit(userRemoteData.putUserProfile(body))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserData(): Flow<UserPreference> {
        return flow {
            Log.d("UserRepository - getUser", userDataStore.getUserPreference().first().toString())
            emit(userDataStore.getUserPreference().first())
        }.flowOn(Dispatchers.IO)
    }
}