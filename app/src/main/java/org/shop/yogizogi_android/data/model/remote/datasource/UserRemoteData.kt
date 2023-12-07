package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.UserService
import org.shop.yogizogi_android.data.model.remote.request.auth.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokFeedResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.TasteRegisterResDTO
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

    override suspend fun getUserMap(header: String, userId: String): Resource<MeokMapResDTO> {
        return processCall { userService.getUserMap(header, userId) }
    }

    override suspend fun getUserFeed(header: String, userId: String): Resource<MeokFeedResDTO> {
        return processCall { userService.getUserFeed(header, userId) }
    }
}