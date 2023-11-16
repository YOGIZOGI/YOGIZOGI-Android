package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("/api/verification/code")
    suspend fun getVerifyCode(@Query("phoneNumber") phoneNumber: String): Response<CommonSuccessRes<VerifyCodeSendResDTO>>

    @GET("/api/verification/check")
    suspend fun getCodeCheck(
        @Query("phoneNumber") phoneNumber: String,
        @Query("code") code: String
    ): Response<CommonSuccessRes<VerifyCodeCheckResDTO>>
}