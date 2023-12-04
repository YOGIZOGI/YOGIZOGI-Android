package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.UserService
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import org.shop.yogizogi_android.di.Auth
import javax.inject.Inject

class UserRemoteData @Inject constructor(@Auth private val userService: UserService) :
    UserRemoteDataSource {
    override suspend fun putUserProfile(body: ProfileCreateReqDTO): Resource<ProfileCreateResDTO> {
        return processCall { userService.putUserProfile(body) }
    }
}