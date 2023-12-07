package org.shop.yogizogi_android.ui.view.main.home

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
import org.shop.yogizogi_android.data.model.remote.request.auth.AddMapReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.AddMapResDTO
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

    private val _userData = MutableStateFlow<UserPreference?>(null)
    val userAccessToken = _userData.asStateFlow()

    private val _moodStoreProcess = MutableStateFlow<Resource<List<SpecificStoreResDTO>>?>(null)
    val moodStoreProcess = _moodStoreProcess.asStateFlow()

    private val _storeInfoProcess = MutableStateFlow<Resource<SpecificStoreResDTO>?>(null)
    val storeInfoProcess = _storeInfoProcess.asStateFlow()

    private val _bookmarkProcess = MutableStateFlow<Resource<AddMapResDTO>?>(null)
    val bookmarkProcess = _bookmarkProcess.asStateFlow()

    private val _bookmarkDelProcess = MutableStateFlow<Resource<String>?>(null)
    val bookmarkDelProcess = _bookmarkDelProcess.asStateFlow()

    init {
        coroutineIOScope.launch {
            _userData.value = userRepository.getUserData().first()
        }
    }

    fun getStoreWithMood(selectedMood: String) {
        viewModelScope.launch {
            _moodStoreProcess.value = Resource.Loading()
            val moods = arrayOf(selectedMood)
            coroutineIOScope.launch {
                _userData.value?.let {
                    storeRepository.getStoreWithMoods(it.accessToken, moods).collect {
                        _moodStoreProcess.value = it
                    }
                }
            }
        }
    }

    fun getStoreInfo(restaurantId: String) {
        viewModelScope.launch {
            _storeInfoProcess.value = Resource.Loading()
            coroutineIOScope.launch {
                _userData.value?.let {
                    storeRepository.getSpecificStore(it.accessToken, restaurantId).collect {
                        _storeInfoProcess.value = it
                    }
                }
            }
        }
    }

    fun addMapRestaurants(restaurantId: String) {
        viewModelScope.launch {
            _bookmarkProcess.value = Resource.Loading()
            val body = _userData.value?.let { AddMapReqDTO(it.id, restaurantId) }
            coroutineIOScope.launch {
                _userData.value?.let {
                    storeRepository.addMapRestaurants(it.accessToken, body!!).collect {
                        _bookmarkProcess.value = it
                    }
                }
            }
        }
    }

    fun deleteMapRestaurants(restaurantId: String) {
        viewModelScope.launch {
            _bookmarkDelProcess.value = Resource.Loading()
            val body = _userData.value?.let { AddMapReqDTO(it.id, restaurantId) }
            coroutineIOScope.launch {
                _userData.value?.let {
                    storeRepository.deleteMapRestaurants(it.accessToken, body!!).collect {
                        _bookmarkDelProcess.value = it
                    }
                }
            }
        }
    }

    fun initStoreInfoProcess() {
        _storeInfoProcess.value = null
    }

    fun initMoodStoreProcess() {
        _moodStoreProcess.value = null
    }
}