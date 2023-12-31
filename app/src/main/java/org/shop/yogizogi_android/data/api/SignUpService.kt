package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.unauth.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.unauth.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.SignUpResDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpService {
    @POST("/api/sign-up")
    suspend fun postSignUp(@Body signUpReq: SignUpReqDTO): Response<CommonSuccessRes<SignUpResDTO>>

    @GET("/api/sign-up/check-duplication-nickname")
    suspend fun getNicknameDupCheck(@Query("nickname") nickname: String): Response<CommonSuccessRes<DupCheckResDTO>>
}