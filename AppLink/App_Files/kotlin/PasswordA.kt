package com.aryan.chatapp_build21911
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aryan.chatapp_build21911.R
import com.aryan.chatapp_build21911.YourActivity

class PasswordA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_message)

        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val buttonCheckPassword: Button = findViewById(R.id.buttonCheckPassword)

        buttonCheckPassword.setOnClickListener {
            // Check if the entered password is "chinmaya"
            if (editTextPassword.text.toString() == "chinmaya") {
                // Password is correct, navigate to YourActivity
                val intent = Intent(this, YourActivity::class.java)
                startActivity(intent)
                finish() // Optional: Close the current activity if needed
            } else {
                Toast.makeText(this, "Incorrect password. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
