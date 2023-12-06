package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.auth.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokFeedResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.TasteRegisterResDTO
import org.shop.yogizogi_android.di.ACCESS_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @GET("/api/users/{userId}/meok-map")
    suspend fun getUserMap(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Path("userId") userId: String
    ):Response<CommonSuccessRes<MeokMapResDTO>>

    @GET("/api/users/{userId}/meok-feed")
    suspend fun getUserFeed(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Path("userId") userId: String
    ):Response<CommonSuccessRes<MeokFeedResDTO>>
}