package org.shop.yogizogi_android.ui.view.main.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import org.shop.yogizogi_android.repository.StoreRepository
import org.shop.yogizogi_android.repository.UserRepository
import org.shop.yogizogi_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository
) :
    BaseViewModel() {
    private val coroutineIOScope = CoroutineScope(Dispatchers.IO)

    private val _userAccessToken = MutableStateFlow<String>("")
    val userAccessToken = _userAccessToken.asStateFlow()

    private val _moodStoreProcess = MutableStateFlow<Resource<SpecificStoreResDTO>?>(null)
    val moodStoreProcess = _moodStoreProcess.asStateFlow()

    init {
        coroutineIOScope.launch {
            _userAccessToken.value = userRepository.getUserData().first().accessToken
        }
    }

    fun getStoreWithMood(selectedMood: String) {
        viewModelScope.launch {
            _moodStoreProcess.value = Resource.Loading()
            val moods = arrayOf(selectedMood)
            Log.e("MoodFragment", moods.toString())
            coroutineIOScope.launch {
                storeRepository.getStoreWithMoods(_userAccessToken.value, moods).collect {
                    _moodStoreProcess.value = it
                }
            }
        }
    }
}