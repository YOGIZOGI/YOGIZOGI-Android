package org.shop.yogizogi_android.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.datasource.SignUpRemoteData
import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val signUpRemoteData: SignUpRemoteData) :
    SignUpRepository {
    override suspend fun postSignUp(body: SignUpReqDTO): Flow<Resource<SignUpResDTO>> {
        return flow {
            emit(signUpRemoteData.postSignUp(body))
        }.flowOn(Dispatchers.IO)
    }
}