package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/api/sign-up")
    suspend fun postSignUp(@Body signUpReq: SignUpReqDTO): Response<CommonSuccessRes<SignUpResDTO>>
}