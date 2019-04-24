package com.jokopriyono.data.remote

import android.net.Uri
import com.jokopriyono.BuildConfig

object JsonApi {
    private fun getBaseUrl(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon().build().toString()
    }

    fun getPosts(): String {
        return Uri.parse(getBaseUrl()).buildUpon().appendPath("posts").build().toString()
    }
}