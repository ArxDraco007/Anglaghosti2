package com.aryan.chatapp_build21911

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        // Open links within the WebView instead of the default browser
        view?.loadUrl(request?.url.toString())
        return true
    }
}
