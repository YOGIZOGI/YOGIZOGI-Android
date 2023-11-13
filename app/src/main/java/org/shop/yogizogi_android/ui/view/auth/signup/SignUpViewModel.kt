package org.shop.yogizogi_android.ui.view.auth.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _signUpStep = MutableStateFlow<Int>(0)
    val signUpStep = _signUpStep.asStateFlow()

    private val _phoneNumber = MutableStateFlow<String>("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _codeReqProcess =
        MutableStateFlow<Resource<VerifyCodeSendResDTO>>(Resource.Loading())
    val codeReqProcess = _codeReqProcess.asStateFlow()

    private val _codeCheckProcess =
        MutableStateFlow<Resource<VerifyCodeCheckResDTO>>(Resource.Loading())
    val codeCheckProcess = _codeCheckProcess.asStateFlow()

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun getVerifyCode() {
        viewModelScope.launch {
            _codeReqProcess.value = Resource.Loading()
            Log.d("SignUpViewModel number value", _phoneNumber.value)
            authRepository.getVerifyCode(_phoneNumber.value).collect {
                _codeReqProcess.value = it
                Log.d("InitialViewModel-codeProcess", _codeReqProcess.value.toString())
            }
        }
    }

    fun checkVerifyCode(phoneNumber: String, code: String) {
        viewModelScope.launch {
            _codeCheckProcess.value = Resource.Loading()
            authRepository.checkVerifyCode(phoneNumber, code).collect {
                _codeCheckProcess.value = it
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