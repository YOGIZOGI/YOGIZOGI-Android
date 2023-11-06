package org.shop.yogizogi_android.di.network

import android.net.ConnectivityManager

interface NetworkConnectivity {
    fun getConnectivityManager(): ConnectivityManager
    fun isConnected(): Boolean
}