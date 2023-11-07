package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO

interface AuthRemoteDataSource {
    suspend fun getVerifyCode(phoneNumber: String): Resource<VerifyCodeSendResDTO>
}