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
import org.shop.yogizogi_android.data.model.remote.request.auth.ProfileCreateReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.TasteRegisterReqDTO
import org.shop.yogizogi_android.data.model.remote.response.unauth.DupCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ProfileCreateResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.TasteRegisterResDTO
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

    private val _userAccessToken = MutableStateFlow<String>("")
    val userAccessToken = _userAccessToken.asStateFlow()

    private val _nicknameDupProcess = MutableStateFlow<Resource<DupCheckResDTO>?>(null)
    val nicknameDupProcess = _nicknameDupProcess.asStateFlow()

    private val _userProfileProcess =
        MutableStateFlow<Resource<ProfileCreateResDTO>?>(null)
    val userProfileProcess = _userProfileProcess.asStateFlow()

    private val _userTasteProcess =
        MutableStateFlow<Resource<TasteRegisterResDTO>?>(null)
    val userTasteProcess = _userTasteProcess.asStateFlow()

    init {
        coroutineIOScope.launch {
            _userAccessToken.value = userRepository.getUserData().first().accessToken
        }
    }

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
                userRepository.createProfile(_userAccessToken.value, profileCreateRequest).collect {
                    _userProfileProcess.value = it
                }
            }
        }
    }

    fun createUserTaste(
        hotPrefer: Long,
        hotWell: Long,
        sweetPrefer: Long,
        sweetWell: Long,
        saltyPrefer: Long,
        saltyWell: Long,
    ) {
        viewModelScope.launch {
            _userTasteProcess.value = Resource.Loading()
            val tasteCreateRequest = TasteRegisterReqDTO(
                hotPrefer,
                hotWell,
                sweetPrefer,
                sweetWell,
                saltyPrefer,
                saltyWell
            )
            coroutineIOScope.launch {
                userRepository.createTaste(_userAccessToken.value, tasteCreateRequest).collect {
                    _userTasteProcess.value = it
                }
            }
        }
    }
}