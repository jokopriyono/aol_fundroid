package com.jokopriyono.ui

import com.jokopriyono.data.remote.response.Posts

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun toast(m: String)
    fun showPosts(posts: MutableList<Posts>)
}