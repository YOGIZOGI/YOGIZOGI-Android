package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.AuthService
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import javax.inject.Inject

class AuthRemoteData @Inject constructor(private val authService: AuthService) :
    AuthRemoteDataSource {

    override suspend fun getVerifyCode(phoneNumber: String): Resource<VerifyCodeSendResDTO> {
        val response = authService.getVerifyCode(phoneNumber)
        return if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(response.message(), null)
        }
    }
}