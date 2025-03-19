package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewPasswordActivity : AppCompatActivity() {
    // Flag to track the visibility of passwords
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_change_pass) // Replace with your actual layout name

        // Initialize views
        val newPasswordEditText = findViewById<EditText>(R.id.et_new_pass)
        val confirmPasswordEditText = findViewById<EditText>(R.id.et_confirm_pass)
        val showPasswordTextView = findViewById<TextView>(R.id.tv_show_password)
        val proceedButton = findViewById<Button>(R.id.btn_proceed)
        val backButton = findViewById<ImageButton>(R.id.btn_back)

        // Set up Back Button functionality
        backButton.setOnClickListener {
            // Finish the current activity to go back to the previous one
            finish()
        }

        // Set up "Show Password" functionality
        showPasswordTextView.setOnClickListener {
            if (isPasswordVisible) {
                // Hide the passwords
                newPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                showPasswordTextView.text = "Show password" // Update the label
            } else {
                // Show the passwords
                newPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                showPasswordTextView.text = "Hide password" // Update the label
            }
            // Retain cursor position
            newPasswordEditText.setSelection(newPasswordEditText.text.length)
            confirmPasswordEditText.setSelection(confirmPasswordEditText.text.length)

            // Toggle the visibility flag
            isPasswordVisible = !isPasswordVisible
        }

        // Set up "Proceed" button functionality
        proceedButton.setOnClickListener {
            // Retrieve the text from input fields
            val newPassword = newPasswordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Validate the input
            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                // Check for empty fields
                Toast.makeText(this, "Please fill in both password fields.", Toast.LENGTH_SHORT).show()
            } else if (newPassword != confirmPassword) {
                // Check if passwords match
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            } else {
                // Passwords match
                Toast.makeText(this, "Passwords match. Proceeding...", Toast.LENGTH_SHORT).show()

                // Simulate successful password reset (example logic)
                resetPassword(newPassword) // Add your server-side or backend logic here

                // Show a success message
                Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_LONG).show()

                // Navigate back to the Login page
                val intent = Intent(this, MainActivity::class.java) // Replace with your actual login activity
                startActivity(intent)

                // Finish this activity to prevent stacking
                finish()
            }
        }
    }

    private fun resetPassword(password: String) {
        // Replace this with actual logic to reset the password (e.g., send data to the server)
    }
}
