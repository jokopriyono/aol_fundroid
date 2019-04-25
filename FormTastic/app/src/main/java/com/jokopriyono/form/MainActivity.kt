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

    private val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = getString(R.string.agreement)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            txt_agreement.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        else
            txt_agreement.text = Html.fromHtml(text)

        checkbox.setOnCheckedChangeListener { _, isChecked -> btn_register.isEnabled = isChecked }
        btn_register.setOnClickListener { validate() }
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
