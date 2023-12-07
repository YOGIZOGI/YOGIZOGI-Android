package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.auth.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokFeedResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.TasteRegisterResDTO

interface UserRemoteDataSource {
    suspend fun putUserProfile(
        header: String,
        body: ProfileCreateReqDTO
    ): Resource<ProfileCreateResDTO>

    suspend fun postMeokProfile(
        header: String,
        body: TasteRegisterReqDTO
    ): Resource<TasteRegisterResDTO>

    suspend fun getUserMap(
        header: String,
        userId: String
    ): Resource<MeokMapResDTO>

    suspend fun getUserFeed(
        header: String,
        userId: String
    ): Resource<MeokFeedResDTO>
}