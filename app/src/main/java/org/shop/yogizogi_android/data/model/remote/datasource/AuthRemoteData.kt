package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.AuthService
import org.shop.yogizogi_android.data.api.SignUpService
import org.shop.yogizogi_android.data.model.remote.request.LogInReqDTO
import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import javax.inject.Inject

class AuthRemoteData @Inject constructor(
    private val authService: AuthService,
    private val signUpService: SignUpService
) :
    AuthRemoteDataSource {
    override suspend fun postLogin(loginBody: LogInReqDTO): Resource<LogInResDTO> {
        return processCall { authService.postLogin(loginBody) }
    }

    override suspend fun getVerifyCode(phoneNumber: String): Resource<VerifyCodeSendResDTO> {
        return processCall { authService.getVerifyCode(phoneNumber) }
    }

    override suspend fun getVerifyCheck(
        phoneNumber: String,
        code: String
    ): Resource<VerifyCodeCheckResDTO> {
        return processCall { authService.getCodeCheck(phoneNumber, code) }
    }

    override suspend fun postSignUp(body: SignUpReqDTO): Resource<SignUpResDTO> {
        return processCall { signUpService.postSignUp(body) }
    }

    override suspend fun getNicknameDupCheck(nickname: String): Resource<DupCheckResDTO> {
        return processCall { signUpService.getNicknameDupCheck(nickname) }
    }
}