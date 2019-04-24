package com.jokopriyono.ui

import com.google.gson.Gson
import com.jokopriyono.data.remote.ApiRepository
import com.jokopriyono.data.remote.JsonApi
import com.jokopriyono.data.remote.response.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(val mainView: MainView, val apiRepository: ApiRepository, val gson: Gson) {
    fun getPosts() {
        mainView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                gson.fromJson(apiRepository.doRequest(JsonApi.getPosts()).await(), arrayOf<Posts>()::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            mainView.hideLoading()
        }
    }
}