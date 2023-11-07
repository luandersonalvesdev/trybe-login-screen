package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val inputEmail: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val inputPassword: TextInputLayout by lazy {
        findViewById(R.id.password_text_input_layout)
    }
    private val btnLogin: Button by lazy { findViewById(R.id.login_button) }

    private val emailRegex = "^[A-Za-z0-9.]+@[A-Za-z]+\\.[A-Za-z]+$".toRegex()
    private val contextView: ConstraintLayout by lazy { findViewById(R.id.main) }

    companion object {
        const val MAX_PASSWORD_LENGTH = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEmail.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateButtonState()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateButtonState()
            }

            override fun afterTextChanged(p0: Editable?) {
                updateButtonState()
            }
        })

        inputPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateButtonState()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateButtonState()
            }

            override fun afterTextChanged(p0: Editable?) {
                updateButtonState()
            }
        })

        btnLogin.setOnClickListener {
            val emailText = inputEmail.editText?.text.toString()
            val passwordText = inputPassword.editText?.text.toString()

            if (!checkEmail(emailText)) {
                inputEmail.error = resources.getString(R.string.email_warning)
            } else {
                inputEmail.error = null
            }

            if (!checkPassword(passwordText)) {
                inputPassword.error = resources.getString(R.string.password_warning)
            } else {
                inputPassword.error = null
            }

            if (checkEmail(emailText) &&
                checkPassword(passwordText)
            ) {
                Snackbar.make(contextView, R.string.login_succeeded, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun updateButtonState() {
        val emailLength = inputEmail.editText?.text.toString().length
        var passwordLength = inputPassword.editText?.text.toString().length

        btnLogin.isEnabled = emailLength > 0 && passwordLength > 0
        btnLogin.isClickable = emailLength > 0 && passwordLength > 0
    }

    private fun checkEmail(email: String): Boolean {
        return email.matches(emailRegex)
    }

    private fun checkPassword(password: String): Boolean {
        return password.length > MAX_PASSWORD_LENGTH
    }
}
