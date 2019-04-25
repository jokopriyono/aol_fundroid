package com.jokopriyono.form

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Patterns
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {
    companion object {
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_PASSWORD = "password"
        private const val KEY_PASSWORD_2 = "password2"
        private const val KEY_EMAIL = "email"
    }

    private val handler: Handler = Handler()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = getString(R.string.agreement)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            txt_agreement.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        else
            txt_agreement.text = Html.fromHtml(text)

        savedInstanceState?.let {
            edt_first_name.setText(it.getString(KEY_FIRST_NAME))
            edt_last_name.setText(it.getString(KEY_LAST_NAME))
            edt_password.setText(it.getString(KEY_PASSWORD))
            edt_confirm_password.setText(it.getString(KEY_PASSWORD_2))
            edt_email.setText(it.getString(KEY_EMAIL))
        }

        checkbox.setOnCheckedChangeListener { _, isChecked -> btn_register.isEnabled = isChecked }
        btn_register.setOnClickListener { validate() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (edt_first_name.toString().isNotEmpty()) outState.putString(KEY_FIRST_NAME, edt_first_name.text.toString())
        if (edt_last_name.toString().isNotEmpty()) outState.putString(KEY_LAST_NAME, edt_last_name.text.toString())
        if (edt_password.toString().isNotEmpty()) outState.putString(KEY_PASSWORD, edt_password.text.toString())
        if (edt_confirm_password.toString().isNotEmpty()) outState.putString(
            KEY_PASSWORD_2,
            edt_confirm_password.text.toString()
        )
        if (edt_email.toString().isNotEmpty()) outState.putString(KEY_EMAIL, edt_email.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun validate() {
        if (edt_first_name.text.isEmpty())
            toast(g(R.string.first_name) + " " + g(R.string.empty))
        else if (edt_first_name.text.length < 2)
            toast(R.string.two_character)
        else if (edt_last_name.text.isEmpty())
            toast(g(R.string.last_name) + " " + g(R.string.empty))
        else if (edt_last_name.text.length < 2)
            toast(R.string.two_character)
        else if (edt_email.text.isEmpty())
            toast(g(R.string.email) + " " + g(R.string.empty))
        else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email.text).matches())
            toast(R.string.invalid_email)
        else if (edt_password.text.isEmpty())
            toast(g(R.string.password) + " " + g(R.string.empty))
        else if (edt_password.text.length < 6)
            toast(R.string.six_character)
        else if (edt_password.text.toString() != edt_confirm_password.text.toString())
            toast(R.string.password_error)
        else
            process()
    }

    private fun g(ids: Int): String = getString(ids)

    private fun process() {
        relative_loading.visibility = VISIBLE
        stateClickable(false)
        handler.postDelayed({
            runOnUiThread {
                relative_loading.visibility = GONE
                stateClickable(true)
                alert(getString(R.string.success)) { yesButton { getString(R.string.ok) } }.show()
            }
        }, 2000)
    }

    private fun stateClickable(b: Boolean) {
        edt_first_name.isEnabled = b
        edt_last_name.isEnabled = b
        edt_password.isEnabled = b
        edt_confirm_password.isEnabled = b
        edt_email.isEnabled = b
        checkbox.isClickable = b
        btn_register.isClickable = b
    }
}
