package com.jokopriyono.data.database

data class PostsColumn(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    companion object {
        const val TABLE_POSTS: String = "posts"
        const val ID_: String = "id_"
        const val ID: String = "id"
        const val USER_ID: String = "user_id"
        const val TITLE: String = "title"
        const val BODY: String = "body"
    }
}