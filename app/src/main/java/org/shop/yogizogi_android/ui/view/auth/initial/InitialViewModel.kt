package org.shop.yogizogi_android.ui.view.auth.initial

import dagger.hilt.android.lifecycle.HiltViewModel
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

}