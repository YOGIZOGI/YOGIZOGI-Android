package org.shop.yogizogi_android.ui.view.auth.signup

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel() {
    private val _signUpStep = MutableStateFlow<Int>(0)
    val signUpStep = _signUpStep.asStateFlow()

    fun stepUp() {
        _signUpStep.value += 1
    }

    fun stepDown() {
        _signUpStep.value -= 1
    }
}