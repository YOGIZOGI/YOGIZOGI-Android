package org.shop.yogizogi_android.ui.view.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.response.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.ProfileCreateResDTO
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.repository.UserRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _nicknameDupProcess = MutableStateFlow<Resource<DupCheckResDTO>>(Resource.Loading())
    val nicknameDupProcess = _nicknameDupProcess.asStateFlow()

    private val _userProfileProcess =
        MutableStateFlow<Resource<ProfileCreateResDTO>>(Resource.Loading())
    val userProfileProcess = _userProfileProcess.asStateFlow()

    fun nicknameDupCheck(nickname: String) {
        viewModelScope.launch {
            _nicknameDupProcess.value = Resource.Loading()
            coroutineIOScope.launch {
                authRepository.nicknameDupCheck(nickname).collect {
                    _nicknameDupProcess.value = it
                }

                // Test
                val user = userRepository.getUserData().first()
                Log.d(
                    "ProfileViewModel - userData",
                    "User UUID: ${user.id}\nUser firstLogInState: ${user.firstLogInStatus}\nUser AccessToken: ${user.accessToken}\nUser RefreshToken: ${user.refreshToken}"
                )
            }
        }
    }

    fun createUserProfile(nickname: String, userImage: String, userIntro: String) {
        viewModelScope.launch {
            _userProfileProcess.value = Resource.Loading()
            val profileCreateRequest = ProfileCreateReqDTO(nickname, userImage, userIntro)
            coroutineIOScope.launch {
                userRepository.createProfile(profileCreateRequest).collect {
                    _userProfileProcess.value = it
                }
            }
        }
    }
}