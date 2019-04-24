package com.jokopriyono.ui

import android.os.Bundle
import android.os.Handler
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jokopriyono.Common
import com.jokopriyono.R
import com.jokopriyono.data.remote.ApiRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, SearchView.OnQueryTextListener {
    private lateinit var mainPresenter: MainPresenter
    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiRepository = ApiRepository()
        val gson = Gson()
        mainPresenter = MainPresenter(this, apiRepository, gson)
        getPosts()

        swipe_refresh.setOnRefreshListener {
            getPosts()
        }
        search_view.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        handler.removeCallbacksAndMessages(null)
        if (query.isNotEmpty()) {
            mainPresenter.searchPost(query, applicationContext)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            query?.let {
                if (query.isNotEmpty()) {
                    runOnUiThread { mainPresenter.searchPost(query, applicationContext) }
                }
            }
        }, 1000)
        return true
    }

    private fun getPosts() {
        if (Common.checkInternet(this)) mainPresenter.getPosts(this)
        else toast(getString(R.string.check_connection))
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
