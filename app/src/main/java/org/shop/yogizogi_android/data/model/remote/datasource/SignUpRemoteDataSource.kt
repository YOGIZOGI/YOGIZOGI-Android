package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO

interface SignUpRemoteDataSource {
    suspend fun postSignUp(body: SignUpReqDTO): Resource<SignUpResDTO>
}