package org.shop.yogizogi_android.repository

import kotlinx.coroutines.flow.Flow
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO

interface SignUpRepository {
    suspend fun postSignUp(body: SignUpReqDTO): Flow<Resource<SignUpResDTO>>
}