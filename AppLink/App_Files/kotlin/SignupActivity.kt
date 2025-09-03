// SignupActivity.kt
package com.aryan.chatapp_build21911
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignup: Button

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        buttonSignup = findViewById(R.id.buttonSignup)
        auth = FirebaseAuth.getInstance()

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignup = findViewById(R.id.buttonSignup)

        buttonSignup.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

                // Store the values in SharedPreferences
                finish()
            // Validate the email and password
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success
                        val user: FirebaseUser? = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign up fails, display a message to the user.
                        val exception = task.exception as FirebaseAuthException
                        Toast.makeText(this, "Signup failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }
    private fun saveUserInfo(username: String, schoolName: String) {
        val sharedPrefs = getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("username", username)
        editor.putString("schoolName", schoolName)
        editor.apply()
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User signed up successfully, navigate to the next screen or perform necessary actions
            startActivity(Intent(this, FleManager::class.java))
            finish()
        } else {
            // Handle the case when signup fails
            // You can update the UI, show an error message, or perform other actions
        }
    }
}
