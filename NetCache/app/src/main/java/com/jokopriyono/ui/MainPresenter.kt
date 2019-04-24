package com.jokopriyono.ui

import android.content.Context
import com.google.gson.Gson
import com.jokopriyono.data.database.PostsColumn
import com.jokopriyono.data.database.database
import com.jokopriyono.data.remote.ApiRepository
import com.jokopriyono.data.remote.JsonApi
import com.jokopriyono.data.remote.response.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MainPresenter(
    private val mainView: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPosts(context: Context) {
        mainView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data =
                    gson.fromJson(
                        apiRepository.doRequest(JsonApi.getPosts()).await(),
                        arrayOf<Posts>()::class.java
                    )
                saveToDatabase(data, context)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            mainView.hideLoading()
        }
    }

    private fun saveToDatabase(data: Array<Posts>, context: Context) {
        try {
            context.database.use {
                delete(PostsColumn.TABLE_POSTS)
                if (data.isNotEmpty()) {
                    for (post: Posts in data) {
                        insert(
                            PostsColumn.TABLE_POSTS,
                            PostsColumn.ID_POST to post.id,
                            PostsColumn.USER_ID to post.userId,
                            PostsColumn.TITLE to post.title,
                            PostsColumn.BODY to post.body
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun searchPost(query: String, context: Context) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val posts: MutableList<Posts> = mutableListOf()
                context.database.use {
                    val result = select(PostsColumn.TABLE_POSTS)
                        .whereArgs(PostsColumn.TITLE + " LIKE '%$query%'")
                        .orderBy(PostsColumn.TITLE)
                    val data = result.parseList(classParser<PostsColumn>())
                    for (row: PostsColumn in data) {
                        val p = Posts(
                            row.userId,
                            row.idPost,
                            row.title,
                            row.body
                        )
                        posts.add(p)
                    }
                    mainView.showPosts(posts)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}