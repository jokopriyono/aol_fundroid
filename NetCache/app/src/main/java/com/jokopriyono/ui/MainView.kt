package com.jokopriyono.ui

import com.jokopriyono.data.remote.response.Posts

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun t(m: String)
    fun showPosts(posts: MutableList<Posts>)
    fun showAlert(m: String)
}