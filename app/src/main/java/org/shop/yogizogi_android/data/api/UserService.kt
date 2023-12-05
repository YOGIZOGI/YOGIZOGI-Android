package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.TasteRegisterResDTO
import org.shop.yogizogi_android.di.ACCESS_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @PUT("/api/users/create-profile")
    suspend fun putUserProfile(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: ProfileCreateReqDTO
    ): Response<CommonSuccessRes<ProfileCreateResDTO>>

    @POST("/api/meok-profile/create")
    suspend fun postMeokProfile(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: TasteRegisterReqDTO
    ): Response<CommonSuccessRes<TasteRegisterResDTO>>
}