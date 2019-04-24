package com.jokopriyono.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jokopriyono.R
import com.jokopriyono.data.remote.ApiRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiRepository = ApiRepository()
        val gson = Gson()
        mainPresenter = MainPresenter(this, apiRepository, gson)
        mainPresenter.getPosts()

    }

    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun toast(m: String) {
        toast(m)
    }
}
