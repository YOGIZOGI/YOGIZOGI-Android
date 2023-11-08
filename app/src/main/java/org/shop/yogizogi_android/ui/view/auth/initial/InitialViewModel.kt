package org.shop.yogizogi_android.ui.view.auth.initial

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.VerifyCodeSendResDTO
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

    private val _codeProcess = MutableStateFlow<Resource<VerifyCodeSendResDTO>>(Resource.Loading())
    val codeProcess = _codeProcess.asStateFlow()

    fun getVerifyCode(phoneNumber: String) {
        viewModelScope.launch {
            _codeProcess.value = Resource.Loading()
            authRepository.getVerifyCode(phoneNumber).collect {
                _codeProcess.value = it
                Log.d("InitialViewModel-codeProcess", codeProcess.value.toString())
            }
        }
    }
}