package org.shop.yogizogi_android.ui.view.auth.initial

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.LogInResDTO
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.repository.UserPreferenceRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _phoneNumber = MutableStateFlow<String>("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    private val _logInProcess = MutableStateFlow<Resource<LogInResDTO>?>(null)
    val logInProcess = _logInProcess.asStateFlow()

    fun login() {
        viewModelScope.launch {
            Log.d("로그인 정보","${_phoneNumber.value}, ${_password.value}")
            _logInProcess.value = Resource.Loading()
            coroutineIOScope.launch {
//                authRepository.login(_phoneNumber.value, _password.value).collect {
//                    _logInProcess.value = it
//                }
                authRepository.login("01012345678", "yogi1234!").collect {
                    _logInProcess.value = it
                }
            }
        }
    }

    fun saveUserData(userData: LogInResDTO) {
        viewModelScope.launch {
            coroutineIOScope.launch {
                userPreferenceRepository.updateUserPreference(userData)
            }
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun updatePassword(password: String) {
        _password.value = password
    }
}