// LoginActivity.kt
package com.aryan.chatapp_build21911
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Check if the app has notification permission
        if (!isNotificationPermissionGranted()) {
            // Request notification permission
            requestNotificationPermission()
        }
        auth = FirebaseAuth.getInstance()

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Validate the email and password
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sign in with email and password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        val user: FirebaseUser? = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        val exception = task.exception

                        if (exception is FirebaseAuthException) {
                            // Handle FirebaseAuthException
                            Toast.makeText(this, "Login failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                        } else {
                            // Handle other exceptions (FirebaseException)
                            Toast.makeText(this, "Login failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
                        }

                        updateUI(null)
                    }

                }
        }
    }
    private val notificationPermissionRequestCode = 123 // You can use any code you prefer

    private fun isNotificationPermissionGranted(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            return notificationManager.isNotificationPolicyAccessGranted
        }
        return true // For versions below Marshmallow, assume the permission is granted
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivityForResult(intent, notificationPermissionRequestCode)
        } else {
            // For versions below Marshmallow, notification permission is granted by default
            Toast.makeText(
                this,
                "Notification permission is granted by default on this device.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Handle the result of the notification permission request
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == notificationPermissionRequestCode) {
            // Check if the user granted notification permission after the request
            if (isNotificationPermissionGranted()) {
                Toast.makeText(
                    this,
                    "Notification permission granted. You can now receive notifications.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Notification permission not granted. You may not receive notifications.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User logged in successfully, navigate to the next screen or perform necessary actions
            startActivity(Intent(this,FleManager::class.java))
            finish()
        } else {
            // Handle the case when login fails
            // You can update the UI, show an error message, or perform other actions
        }
    }
    fun onSignUpButtonClick(view: View) {
        // Start the SignUpActivity
        val signUpIntent = Intent(this, SignupActivity::class.java)
        startActivity(signUpIntent)
    }
}
