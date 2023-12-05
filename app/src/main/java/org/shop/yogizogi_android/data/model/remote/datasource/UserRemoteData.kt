package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.UserService
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.TasteRegisterResDTO
import org.shop.yogizogi_android.di.Auth
import javax.inject.Inject

class UserRemoteData @Inject constructor(private val userService: UserService) :
    UserRemoteDataSource {
    override suspend fun putUserProfile(
        header: String,
        body: ProfileCreateReqDTO
    ): Resource<ProfileCreateResDTO> {
        return processCall { userService.putUserProfile(header, body) }
    }

    override suspend fun postMeokProfile(
        header: String,
        body: TasteRegisterReqDTO
    ): Resource<TasteRegisterResDTO> {
        return processCall { userService.postMeokProfile(header, body) }
    }
}