package com.jokopriyono.data.database

data class PostsColumn(
    val id: Long,
    val idPost: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    companion object {
        const val TABLE_POSTS: String = "posts"
        const val ID_: String = "ID_"
        const val ID_POST: String = "idPost"
        const val USER_ID: String = "userId"
        const val TITLE: String = "title"
        const val BODY: String = "body"
    }
}