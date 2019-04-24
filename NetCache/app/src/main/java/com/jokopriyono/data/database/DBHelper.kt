package com.jokopriyono.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "netcache.db", null, 1) {
    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(context: Context): DBHelper {
            instance?.let { instance = DBHelper(context.applicationContext) }
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            PostsColumn.TABLE_POSTS, true,
            PostsColumn.ID to INTEGER + UNIQUE,
            PostsColumn.USER_ID to INTEGER,
            PostsColumn.TITLE to TEXT,
            PostsColumn.BODY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("", true)
    }
}

val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)