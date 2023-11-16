package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.SignUpService
import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO
import javax.inject.Inject

class SignUpRemoteData @Inject constructor(private val signUpService: SignUpService) :
    SignUpRemoteDataSource {

    override suspend fun postSignUp(body: SignUpReqDTO): Resource<SignUpResDTO> {
        return processCall { signUpService.postSignUp(body) }
    }
}