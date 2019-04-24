package com.jokopriyono.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "netcache.db", null, 1) {
    companion object {
        var instance: DBHelper? = null

        @Synchronized
        fun getInstance(context: Context): DBHelper {
            if (instance == null)
                instance = DBHelper(context.applicationContext)
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            PostsColumn.TABLE_POSTS, true,
            PostsColumn.ID_ to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            PostsColumn.ID_POST to INTEGER + NOT_NULL,
            PostsColumn.USER_ID to INTEGER + NOT_NULL,
            PostsColumn.TITLE to TEXT + NOT_NULL,
            PostsColumn.BODY to TEXT + NOT_NULL
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(PostsColumn.TABLE_POSTS, true)
    }
}

val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)