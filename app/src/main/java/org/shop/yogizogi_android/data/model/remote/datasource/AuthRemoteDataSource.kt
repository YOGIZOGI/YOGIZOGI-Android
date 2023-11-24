package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.LogInReqDTO
import org.shop.yogizogi_android.data.model.remote.response.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO

interface AuthRemoteDataSource {
    suspend fun postLogin(loginBody: LogInReqDTO): Resource<LogInResDTO>
    suspend fun getVerifyCode(phoneNumber: String): Resource<VerifyCodeSendResDTO>
    suspend fun getVerifyCheck(phoneNumber: String, code: String): Resource<VerifyCodeCheckResDTO>
    suspend fun getNicknameDupCheck(nickname: String): Resource<DupCheckResDTO>
}