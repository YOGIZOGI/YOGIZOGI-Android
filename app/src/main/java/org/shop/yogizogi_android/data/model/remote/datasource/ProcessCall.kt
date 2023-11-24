package org.shop.yogizogi_android.data.model.remote.datasource

import android.util.Log
import com.google.gson.Gson
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.CommonFailRes
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import retrofit2.Response

suspend fun <T> processCall(call: suspend () -> Response<CommonSuccessRes<T>>): Resource<T> {
    val response = call()
    return if (response.isSuccessful) {
        val commonSuccessRes = response.body()
        if (commonSuccessRes != null && commonSuccessRes.status == "SUCCESS") {
            Resource.Success(commonSuccessRes.data)
        } else {
            Resource.Error("Server error", null)
        }
    } else {
        Log.d("processCall - FailRes", response.toString())
        val commonFailRes = response.errorBody()?.let {
            Gson().fromJson(it.toString(), CommonFailRes::class.java)
        }
        val errorMessage = commonFailRes?.message ?: "Server error"
        Resource.Error(errorMessage, null)
    }
}