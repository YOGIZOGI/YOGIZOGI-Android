package org.shop.yogizogi_android.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    // LiveData를 사용하여 메시지를 전달하는 데 사용됩니다.
    private val _showToastMessage = MutableLiveData<String>()
    val showToastMessage: LiveData<String>
        get() = _showToastMessage

    // 에러 발생 시 메시지를 전달하는 데 사용됩니다.
    private val _showErrorMessage = MutableLiveData<String>()
    val showErrorMessage: LiveData<String>
        get() = _showErrorMessage

    /**
     * 메시지를 보여주는 데 사용됩니다.
     */
    protected fun showToast(message: String) {
        _showToastMessage.postValue(message)
    }

    /**
     * 에러 메시지를 보여주는 데 사용됩니다.
     */
    protected fun showError(message: String) {
        _showErrorMessage.postValue(message)
    }
}