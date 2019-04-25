package com.jokopriyono.form

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkbox.setOnCheckedChangeListener { _, isChecked -> btn_register.isEnabled = isChecked }
        btn_register.setOnClickListener { validate() }
    }

    private fun validate() {
        if (edt_first_name.text.isEmpty())
            toast(R.string.empty)
        else if (edt_first_name.text.length < 2)
            toast(R.string.two_character)
        else if (edt_last_name.text.isEmpty())
            toast(R.string.empty)
        else if (edt_last_name.text.length < 2)
            toast(R.string.two_character)
        else if (edt_email.text.isEmpty())
            toast(R.string.empty)
        else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email.text).matches())
            toast(R.string.invalid_email)
        else if (edt_password.text.isEmpty())
            toast(R.string.empty)
        else if (edt_password.text.length < 6)
            toast(R.string.six_character)
        else if (edt_password.text.toString() != edt_confirm_password.text.toString())
            toast(R.string.password_error)
        else
            process()
    }

    private fun process() {

    }
}
