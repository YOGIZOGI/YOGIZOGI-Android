package org.shop.yogizogi_android.repository

import kotlinx.coroutines.flow.Flow
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.unauth.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.SignUpResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.VerifyCodeSendResDTO

interface AuthRepository {
    suspend fun login(phoneNumber: String, password: String): Flow<Resource<LogInResDTO>>
    suspend fun getVerifyCode(phoneNumber: String): Flow<Resource<VerifyCodeSendResDTO>>
    suspend fun checkVerifyCode(
        phoneNumber: String,
        code: String
    ): Flow<Resource<VerifyCodeCheckResDTO>>
    suspend fun postSignUp(body: SignUpReqDTO): Flow<Resource<SignUpResDTO>>
    suspend fun nicknameDupCheck(nickname: String): Flow<Resource<DupCheckResDTO>>
}