package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface UserService {
    @PUT("/api/users/create-profile")
    suspend fun putUserProfile(@Body body: ProfileCreateReqDTO): Response<CommonSuccessRes<ProfileCreateResDTO>>
}