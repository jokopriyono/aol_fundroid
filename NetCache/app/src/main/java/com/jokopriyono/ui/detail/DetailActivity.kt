package com.jokopriyono.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.jokopriyono.R
import com.jokopriyono.data.remote.response.Posts
import com.jokopriyono.ui.main.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

class DetailActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private var post: Posts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        post = if (savedInstanceState == null) intent.getParcelableExtra(MainActivity.INTENT_DATA)
        else intent.getParcelableExtra(MainActivity.INTENT_DATA) as Posts

        verticalLayout {
            lparams(width = MATCH_PARENT, height = MATCH_PARENT)
            toolbar = toolbar {
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                navigationIcon = ContextCompat.getDrawable(context, R.drawable.v_left)
                title = getString(R.string.app_name)
                setTitleTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
            verticalLayout {
                lparams(width = MATCH_PARENT, height = MATCH_PARENT)
                padding = dip(16)
                textView {
                    post?.let { text = it.title }
                    textSize = 20f
                    setTypeface(typeface, Typeface.BOLD)
                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                }.lparams {
                    width = MATCH_PARENT
                    height = WRAP_CONTENT
                    bottomMargin = dip(10)
                }
                textView(post?.body)
            }
        }

        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        post?.let { outState.putParcelable(MainActivity.INTENT_DATA, it) }
        super.onSaveInstanceState(outState)
    }
}