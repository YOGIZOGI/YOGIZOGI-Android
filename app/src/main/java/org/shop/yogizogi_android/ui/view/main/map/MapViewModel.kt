package org.shop.yogizogi_android.ui.view.main.map

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
import org.shop.yogizogi_android.data.model.remote.response.auth.MeokMapResDTO
import org.shop.yogizogi_android.repository.UserRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _userData = MutableStateFlow<UserPreference?>(null)
    val userAccessToken = _userData.asStateFlow()

    private val _userMapProcess = MutableStateFlow<Resource<List<MeokMapResDTO>>?>(null)
    val userMapProcess = _userMapProcess.asStateFlow()

    init {
        coroutineIOScope.launch {
            _userData.value = userRepository.getUserData().first()
        }
    }

    fun getUserMap() {
        viewModelScope.launch {
            if (_userData.value == null) {
                _userMapProcess.value = Resource.Error("올바르지 못한 접근입니다.")
            } else {
                _userMapProcess.value = Resource.Loading()
                coroutineIOScope.launch {
                    userRepository.getUserMap(_userData.value!!.accessToken, _userData.value!!.id)
                        .collect {
                            _userMapProcess.value = it
                        }
                }
            }
        }
    }

    fun initMapProcess() {
        _userMapProcess.value = null
    }
}