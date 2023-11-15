package org.shop.yogizogi_android.ui.view.auth.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeCheckResDTO
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
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

    fun stepUp() {
        _signUpStep.value += 1
    }

    fun stepDown() {
        _signUpStep.value -= 1
    }
}