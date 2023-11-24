package org.shop.yogizogi_android.ui.view.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.DupCheckResDTO
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _nicknameDupProcess = MutableStateFlow<Resource<DupCheckResDTO>>(Resource.Loading())
    val nicknameDupProcess = _nicknameDupProcess.asStateFlow()

    fun nicknameDupCheck(nickname: String) {
        viewModelScope.launch {
            _nicknameDupProcess.value = Resource.Loading()
            coroutineIOScope.launch {
                authRepository.nicknameDupCheck(nickname).collect {
                    _nicknameDupProcess.value = it
                }
            }
        }
    }
}