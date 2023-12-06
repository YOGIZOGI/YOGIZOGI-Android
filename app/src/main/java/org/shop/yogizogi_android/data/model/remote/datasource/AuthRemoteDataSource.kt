package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.unauth.LogInReqDTO
import org.shop.yogizogi_android.data.model.remote.request.unauth.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.SignUpResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.VerifyCodeSendResDTO

interface AuthRemoteDataSource {
    suspend fun postLogin(loginBody: LogInReqDTO): Resource<LogInResDTO>
    suspend fun getVerifyCode(phoneNumber: String): Resource<VerifyCodeSendResDTO>
    suspend fun getVerifyCheck(phoneNumber: String, code: String): Resource<VerifyCodeCheckResDTO>
    suspend fun postSignUp(body: SignUpReqDTO): Resource<SignUpResDTO>
    suspend fun getNicknameDupCheck(nickname: String): Resource<DupCheckResDTO>
}