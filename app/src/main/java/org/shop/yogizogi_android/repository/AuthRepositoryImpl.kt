package org.shop.yogizogi_android.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.datasource.AuthRemoteData
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authRemoteData: AuthRemoteData) :
    AuthRepository {

    override suspend fun getVerifyCode(phoneNumber: String): Flow<Resource<VerifyCodeSendResDTO>> {
        return flow {
            emit(authRemoteData.getVerifyCode(phoneNumber))
        }.flowOn(Dispatchers.IO)
    }
}