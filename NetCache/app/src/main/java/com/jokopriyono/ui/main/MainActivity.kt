package com.jokopriyono.ui.main

import android.content.SharedPreferences
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
import com.jokopriyono.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), MainView, SearchView.OnQueryTextListener {
    companion object {
        private const val SHARED_PREFERENCE = "netcachepref"
        const val KEY_PULL = "pull"
        const val KEY_QUERY = "query"
        const val INTENT_DATA = "data"
    }

    private lateinit var mainPresenter: MainPresenter
    private lateinit var searchView: SearchView
    private lateinit var sharedPref: SharedPreferences
    private var handler: Handler = Handler()
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiRepository = ApiRepository()
        val gson = Gson()
        sharedPref = applicationContext.getSharedPreferences(SHARED_PREFERENCE, 0)
        mainPresenter = MainPresenter(this, apiRepository, gson, sharedPref, applicationContext)

        val alreadyPull = sharedPref.getBoolean(KEY_PULL, false)
        if (!alreadyPull) getPosts()
        else {
            if (savedInstanceState != null) query = savedInstanceState.getString(KEY_QUERY)
            else mainPresenter.searchPost("")
        }
        swipe_refresh.setOnRefreshListener {
            getPosts()
        }
        setSupportActionBar(toolbar)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (searchView.query.toString().isNotEmpty()) outState.putString(KEY_QUERY, searchView.query.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuSearch = menu.findItem(R.id.menu_search)
        searchView = menuSearch.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
        query?.let {
            if (it.isNotEmpty()) {
                menuSearch.expandActionView()
                searchView.onActionViewExpanded()
                searchView.isIconified = false
                searchView.setQuery(it, true)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchView.clearFocus()
        handler.removeCallbacksAndMessages(null)
        if (query.isNotEmpty()) {
            mainPresenter.searchPost(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            query?.let {
                if (query.isNotEmpty()) {
                    runOnUiThread { mainPresenter.searchPost(query) }
                }
            }
        }, 1000)
        return true
    }

    private fun getPosts() {
        if (Common.checkInternet(this)) mainPresenter.getPosts()
        else {
            toast(getString(R.string.check_connection))
            swipe_refresh.isRefreshing = false
        }
    }

    override fun showAlert(m: String) {
        alert(m) {
            yesButton { getString(R.string.ok) }
        }.show()
    }

    override fun showPosts(posts: MutableList<Posts>) {
        val titles: MutableList<String> = mutableListOf()
        for (post: Posts in posts) {
            titles.add(post.title)
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles)
        list_view.adapter = adapter
        list_view.setOnItemClickListener { _, _, pos: Int, _: Long ->
            startActivity<DetailActivity>(INTENT_DATA to posts[pos])
        }
    }

    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun t(m: String) {
        toast(m)
    }
}
