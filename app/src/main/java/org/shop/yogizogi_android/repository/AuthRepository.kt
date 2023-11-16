package org.shop.yogizogi_android.repository

import kotlinx.coroutines.flow.Flow
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO

interface AuthRepository {
    suspend fun getVerifyCode(phoneNumber: String): Flow<Resource<VerifyCodeSendResDTO>>

    suspend fun checkVerifyCode(
        phoneNumber: String,
        code: String
    ): Flow<Resource<VerifyCodeCheckResDTO>>
}