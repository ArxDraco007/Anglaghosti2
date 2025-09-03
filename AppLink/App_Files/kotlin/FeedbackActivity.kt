package com.aryan.chatapp_build21911

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

// FeedbackActivity.kt
// FeedbackActivity.kt
class FeedbackActivity : AppCompatActivity() {

    private lateinit var webViewGoogleForm: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        // Initialize WebView
        webViewGoogleForm = findViewById(R.id.webViewGoogleForm)

        // Enable JavaScript
        val webSettings: WebSettings = webViewGoogleForm.settings
        webSettings.javaScriptEnabled = true

        // Load Google Form URL
        val googleFormUrl = "https://docs.google.com/forms/d/e/1FAIpQLSfsovDwtPtO_bCTWSjIF3BlHntHa2Xamv_C5qLDuuc4tuOX7A/viewform?usp=sf_link"
        webViewGoogleForm.loadUrl(googleFormUrl)

        // Other initialization code for your activity
    }
}

