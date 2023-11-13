package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.AuthService
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import javax.inject.Inject

class AuthRemoteData @Inject constructor(private val authService: AuthService) :
    AuthRemoteDataSource {

    override suspend fun getVerifyCode(phoneNumber: String): Resource<VerifyCodeSendResDTO> {
        return processCall { authService.getVerifyCode(phoneNumber) }
    }

    override suspend fun getVerifyCheck(
        phoneNumber: String,
        code: String
    ): Resource<VerifyCodeCheckResDTO> {
        return processCall { authService.getCodeCheck(phoneNumber, code) }
    }
}