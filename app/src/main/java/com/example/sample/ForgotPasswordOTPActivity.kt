package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordOTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_otp)

        val backButton = findViewById<ImageButton>(R.id.btn_back)

        backButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordEmailActivity::class.java) // Change to MainActivity if needed
            startActivity(intent)
            finish()
        }

        val otp1 = findViewById<EditText>(R.id.et_otp1)
        val otp2 = findViewById<EditText>(R.id.et_otp2)
        val otp3 = findViewById<EditText>(R.id.et_otp3)
        val otp4 = findViewById<EditText>(R.id.et_otp4)
        val otp5 = findViewById<EditText>(R.id.et_otp5)
        val otp6 = findViewById<EditText>(R.id.et_otp6)

        // Set up OTP inputs with backspace handling
        setupOTPInput(otp1, otp2, null)
        setupOTPInput(otp2, otp3, otp1)
        setupOTPInput(otp3, otp4, otp2)
        setupOTPInput(otp4, otp5, otp3)
        setupOTPInput(otp5, otp6, otp4)
        setupOTPInput(otp6, null, otp5)

        // Submit Button Logic
        val submitButton = findViewById<Button>(R.id.btn_submit)
        submitButton.setOnClickListener {
            // Concatenate OTP values
            val otp = "${otp1.text}${otp2.text}${otp3.text}${otp4.text}${otp5.text}${otp6.text}"

            // Validate OTP or Proceed with the Workflow
            if (otp.length == 6) {
                // Navigate to the new screen (XML layout)
                val intent = Intent(this, NewPasswordActivity::class.java) // Replace with your target activity
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter a valid 6-digit OTP.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupOTPInput(currentEditText: EditText, nextEditText: EditText?, previousEditText: EditText? = null) {
        currentEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    nextEditText?.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Handle backspace key
        currentEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (currentEditText.text.isEmpty()) {
                    previousEditText?.requestFocus()
                }
            }
            false
        }
    }
}
