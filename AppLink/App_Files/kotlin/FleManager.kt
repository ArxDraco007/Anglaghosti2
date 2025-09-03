package com.aryan.chatapp_build21911
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FleManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flemanage)

        val buttonCalendar: Button = findViewById(R.id.buttonCalendar)
        val buttonAdminMessage: Button = findViewById(R.id.buttonAdminMessage)
        val buttonFeedback: Button = findViewById(R.id.buttonFeedback)

        // Set click listeners for each button
        buttonCalendar.setOnClickListener {
            // Open CalendarActivity when Calendar button is clicked
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        buttonAdminMessage.setOnClickListener {
            // Open YourActivity when Admin Message button is clicked
            val intent = Intent(this, PasswordA::class.java)
            startActivity(intent)
        }

        buttonFeedback.setOnClickListener {
            // Open FeedbackActivity when Feedback button is clicked
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }
    }
}
