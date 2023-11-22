package org.shop.yogizogi_android.ui.view.auth.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.SignUpReqDTO
import org.shop.yogizogi_android.data.model.remote.response.LogInResDTO
import org.shop.yogizogi_android.data.model.remote.response.SignUpResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.repository.SignUpRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val signUpRepository: SignUpRepository
) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _signUpStep = MutableStateFlow<Int>(0)
    val signUpStep = _signUpStep.asStateFlow()

    private val _phoneNumber = MutableStateFlow<String>("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _codeReqProcess =
        MutableStateFlow<Resource<VerifyCodeSendResDTO>>(Resource.Loading())
    val codeReqProcess = _codeReqProcess.asStateFlow()

    private val _codeNumber = MutableStateFlow<String>("")
    val codeNumber = _codeNumber.asStateFlow()

    private val _codeCheckProcess =
        MutableStateFlow<Resource<VerifyCodeCheckResDTO>>(Resource.Loading())
    val codeCheckProcess = _codeCheckProcess.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    private val _passwordCheck = MutableStateFlow<String>("")
    val passwordCheck = _passwordCheck.asStateFlow()

    private val _logInProcess = MutableStateFlow<Resource<LogInResDTO>>(Resource.Loading())
    val logInProcess = _logInProcess.asStateFlow()

    private val _signUpProcess = MutableStateFlow<Resource<SignUpResDTO>>(Resource.Loading())
    val signUpProcess = _signUpProcess.asStateFlow()

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun getVerifyCode() {
        viewModelScope.launch {
            _codeReqProcess.value = Resource.Loading()
            coroutineIOScope.launch {
                authRepository.getVerifyCode(_phoneNumber.value).collect {
                    _codeReqProcess.value = it
                }
            }
        }
    }

    fun updateCode(code: String) {
        _codeNumber.value = code
    }

    fun checkVerifyCode() {
        viewModelScope.launch {
            _codeCheckProcess.value = Resource.Loading()
            coroutineIOScope.launch {
                authRepository.checkVerifyCode(_phoneNumber.value, _codeNumber.value).collect {
                    _codeCheckProcess.value = it
                }
            }
        }
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updatePasswordCheck(passwordCheck: String) {
        _passwordCheck.value = passwordCheck
    }

    fun signUp() {
        viewModelScope.launch {
            val signUpInfo = SignUpReqDTO(_phoneNumber.value, _passwordCheck.value)
            _signUpProcess.value = Resource.Loading()
            coroutineIOScope.launch {
                signUpRepository.postSignUp(signUpInfo).collect {
                    _signUpProcess.value = it
                }
            }
        }
    }

    fun stepUp() {
        _signUpStep.value += 1
    }

    fun stepDown() {
        _signUpStep.value -= 1
    }
}