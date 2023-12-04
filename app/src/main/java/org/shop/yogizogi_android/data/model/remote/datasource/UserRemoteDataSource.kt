package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO

interface UserRemoteDataSource {
    suspend fun putUserProfile(body: ProfileCreateReqDTO): Resource<ProfileCreateResDTO>
}