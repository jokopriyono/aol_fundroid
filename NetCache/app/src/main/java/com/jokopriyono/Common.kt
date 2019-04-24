package com.jokopriyono

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Common {
    /**
     * Default false, true if you had internet connection
     */
    fun checkInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo: NetworkInfo? = null
        return if (netInfo == null) {
            netInfo = cm.activeNetworkInfo
            if (netInfo == null) return false
            when (netInfo.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                else -> false
            }
        } else false
    }
}