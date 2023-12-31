package org.shop.yogizogi_android.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.datasource.AuthRemoteData
import org.shop.yogizogi_android.data.model.remote.request.unauth.LogInReqDTO
import org.shop.yogizogi_android.data.model.remote.request.unauth.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.SignUpResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.VerifyCodeSendResDTO
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authRemoteData: AuthRemoteData) :
    AuthRepository {
    override suspend fun login(phoneNumber: String, password: String): Flow<Resource<LogInResDTO>> {
        val loginBody = LogInReqDTO(phoneNumber, password)
        return flow {
            emit(authRemoteData.postLogin(loginBody))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getVerifyCode(phoneNumber: String): Flow<Resource<VerifyCodeSendResDTO>> {
        return flow {
            emit(authRemoteData.getVerifyCode(phoneNumber))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun checkVerifyCode(
        phoneNumber: String,
        code: String
    ): Flow<Resource<VerifyCodeCheckResDTO>> {
        return flow {
            emit(authRemoteData.getVerifyCheck(phoneNumber, code))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun postSignUp(body: SignUpReqDTO): Flow<Resource<SignUpResDTO>> {
        return flow {
            emit(authRemoteData.postSignUp(body))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun nicknameDupCheck(nickname: String): Flow<Resource<DupCheckResDTO>> {
        return flow {
            emit(authRemoteData.getNicknameDupCheck(nickname))
        }.flowOn(Dispatchers.IO)
    }
}