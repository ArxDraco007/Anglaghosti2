package com.aryan.chatapp_build21911
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Check if the notification permission is grante
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        // Enable disk persistence (optional, for offline capabilities)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}
