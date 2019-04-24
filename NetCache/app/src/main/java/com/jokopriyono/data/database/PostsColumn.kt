package com.jokopriyono.data.database

data class PostsColumn(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    companion object {
        const val TABLE_POSTS: String = "T_POSTS"
        const val ID: String = "ID_"
        const val USER_ID: String = "USER_ID"
        const val TITLE: String = "TITLE"
        const val BODY: String = "BODY"
    }
}