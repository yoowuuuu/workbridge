package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_email)

        // Initialize the EditText for phone number and Continue Button
        val phoneNumberEditText = findViewById<EditText>(R.id.et_phone_number)
        val continueButton = findViewById<Button>(R.id.btn_proceed)

        // Initialize the Back Button
        val backButton = findViewById<ImageButton>(R.id.btn_back)

        // Set up the Back Button click listener
        backButton.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity
        }

        // Set up the Continue Button click listener
        continueButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString().trim()

            // Validate phone number input: Must be 11 digits
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number.", Toast.LENGTH_SHORT).show()
            } else if (phoneNumber.length != 11 || !phoneNumber.matches(Regex("^\\d{11}\$"))) {
                Toast.makeText(this, "Please enter a valid 11-digit phone number.", Toast.LENGTH_SHORT).show()
            } else {
                // Proceed to OTP screen if input is valid
                val intent = Intent(this, ForgotPasswordOTPActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
