package org.shop.yogizogi_android.ui.view.main.myreview

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokFeedResDTO
import org.shop.yogizogi_android.repository.UserRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _userData = MutableStateFlow<UserPreference?>(null)
    val userAccessToken = _userData.asStateFlow()

    private val _userFeedProcess = MutableStateFlow<Resource<MeokFeedResDTO>?>(null)
    val userFeedProcess = _userFeedProcess.asStateFlow()

    init {
        coroutineIOScope.launch {
            _userData.value = userRepository.getUserData().first()
        }
    }

    fun getUserFeed() {
        viewModelScope.launch {
            if (_userData.value == null) {
                _userFeedProcess.value = Resource.Error("올바르지 못한 접근입니다.")
            } else {
                _userFeedProcess.value = Resource.Loading()
                coroutineIOScope.launch {
                    userRepository.getUserFeed(_userData.value!!.accessToken, _userData.value!!.id)
                        .collect {
                            _userFeedProcess.value = it
                        }
                }
            }
        }
    }
}