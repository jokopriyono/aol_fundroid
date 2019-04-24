package com.jokopriyono

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Common {
    fun checkInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo?
        netInfo = cm.activeNetworkInfo
        return when (netInfo.type) {
            ConnectivityManager.TYPE_WIFI -> true
            ConnectivityManager.TYPE_MOBILE -> true
            else -> false
        }
    }
}