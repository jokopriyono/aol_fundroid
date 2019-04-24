package com.jokopriyono.ui

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.gson.Gson
import com.jokopriyono.Common
import com.jokopriyono.R
import com.jokopriyono.data.remote.ApiRepository
import com.jokopriyono.data.remote.response.Posts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, SearchView.OnQueryTextListener {
    private lateinit var mainPresenter: MainPresenter
    private var handler: Handler = Handler()
    private lateinit var searchView: SearchView

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
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuSearch = menu.findItem(R.id.menu_search)
        searchView = menuSearch.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchView.clearFocus()
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

    override fun showPosts(posts: MutableList<Posts>) {
        val titles: MutableList<String> = mutableListOf()
        for (post: Posts in posts) {
            titles.add(post.title)
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles)
        list_view.adapter = adapter
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
