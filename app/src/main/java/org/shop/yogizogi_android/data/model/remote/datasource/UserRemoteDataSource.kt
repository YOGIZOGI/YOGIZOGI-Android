package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.TasteRegisterResDTO

interface UserRemoteDataSource {
    suspend fun putUserProfile(
        header: String,
        body: ProfileCreateReqDTO
    ): Resource<ProfileCreateResDTO>

    suspend fun postMeokProfile(
        header: String,
        body: TasteRegisterReqDTO
    ): Resource<TasteRegisterResDTO>
}