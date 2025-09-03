package com.aryan.chatapp_build21911

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class YourActivity : AppCompatActivity() {

    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var messageTextView: TextView
    private var notificationId = 1
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your)
        val viewFeedbackButton: Button = findViewById(R.id.buttonViewFeedback)
        messageEditText = findViewById(R.id.editTextMessage)
        sendButton = findViewById(R.id.buttonSend)
        messageTextView = findViewById(R.id.textViewMessages)
        webView = findViewById(R.id.your_webview_id)
        val openExternallyButton: Button = findViewById(R.id.openExternallyButton)
        // Enable JavaScript in WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Set WebView client to handle page loading events
        webView.webViewClient = MyWebViewClient()
        openExternallyButton.setOnClickListener {
            val sheetUrl = "https://docs.google.com/spreadsheets/d/1JNM-e_e491EVQgBa9-8V_lR6rgaIR0m0nPukjsFfXRo/edit?usp=sharing"
            openGoogleSheetExternally(sheetUrl)
        }

        // Set button click listener
        viewFeedbackButton.setOnClickListener {
            val sheetUrl = "https://docs.google.com/spreadsheets/d/1JNM-e_e491EVQgBa9-8V_lR6rgaIR0m0nPukjsFfXRo/edit?usp=sharing"
            openGoogleSheet(sheetUrl)
        }
        createNotificationChannel()

        sendButton.setOnClickListener {
            val messageContent = messageEditText.text.toString().trim()

            if (messageContent.isNotEmpty()) {
                // Display the message in the TextView
                messageTextView.append("$messageContent\n")
                // Clear the EditText after sending
                messageEditText.text.clear()

                // Send a notification with the entered message
                sendNotification(messageContent)
            } else {
                // Show a toast if the EditText is empty
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            VIBRATE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, send the notification now
                    // You may want to check again in case the user denied the permission
                    sendNotification(messageEditText.text.toString().trim())
                } else {
                    // Permission denied, handle accordingly
                    Toast.makeText(this, "Vibration permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private val VIBRATE_REQUEST_CODE = 123
    private fun sendNotification(messageText: String) {
        if (checkSelfPermission(android.Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted, proceed to send notification
            val builder = NotificationCompat.Builder(this, "your_channel_id")
                .setSmallIcon(R.drawable.cirs)
                .setContentTitle("ADMIN:")
                .setContentText(messageText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, builder.build())
                notificationId++
            }
        } else {
            // Permission is not granted, request it
            requestPermissions(arrayOf(android.Manifest.permission.VIBRATE), VIBRATE_REQUEST_CODE)
        }
        val builder = NotificationCompat.Builder(this, "your_channel_id")
            .setSmallIcon(R.drawable.cirs)
            .setContentTitle("ADMIN:")
            .setContentText(messageText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
            notificationId++
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "your_channel_id",
                "Your Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Your Channel Description"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun openGoogleSheet(sheetUrl: String) {
        webView.loadUrl(sheetUrl)
    }
    private fun openGoogleSheetExternally(sheetUrl: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(sheetUrl))
        startActivity(browserIntent)
    }
}