package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val inputEmail: TextInputEditText by lazy { findViewById(R.id.email_input_layout) }
    private val inputPassword: TextInputEditText by lazy {
        findViewById(R.id.password_input_layout)
    }
    private val btnLogin: Button by lazy { findViewById(R.id.login_button) }
    private val txWrongEmail: TextView by lazy { findViewById(R.id.tx_wrong_email) }
    private val txWrongPassword: TextView by lazy { findViewById(R.id.tx_wrong_password) }

    private val emailRegex = "^[A-Za-z0-9.]+@[A-Za-z]+\\.[A-Za-z]+$".toRegex()
    private val contextView: ConstraintLayout by lazy { findViewById(R.id.main) }

    companion object {
        const val MAX_PASSWORD_LENGTH = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEmail.addTextChangedListener(object : TextWatcher {
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

        inputPassword.addTextChangedListener(object : TextWatcher {
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
            if (!checkEmail(inputEmail.text.toString())) {
                changeVisibility(txWrongEmail, true)
            } else {
                changeVisibility(txWrongEmail, false)
            }

            if (!checkPassword(inputPassword.text.toString())) {
                changeVisibility(txWrongPassword, true)
            } else {
                changeVisibility(txWrongPassword, false)
            }

            if (checkEmail(inputEmail.text.toString()) &&
                checkPassword(inputPassword.text.toString())
            ) {
                Snackbar.make(contextView, R.string.login_succeeded, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun updateButtonState() {
        val emailLength = inputEmail.text.toString().length
        var passwordLength = inputPassword.text.toString().length

        btnLogin.isEnabled = emailLength > 0 && passwordLength > 0
        btnLogin.isClickable = emailLength > 0 && passwordLength > 0
    }

    private fun checkEmail(email: String): Boolean {
        return email.matches(emailRegex)
    }

    private fun checkPassword(password: String): Boolean {
        return password.length > MAX_PASSWORD_LENGTH
    }

    private fun changeVisibility(v: View, visibility: Boolean) {
        if (visibility) {
            v.visibility = View.VISIBLE
        } else {
            v.visibility = View.GONE
        }
    }
}
