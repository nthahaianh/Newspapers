package com.example.newspapers.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.newspapers.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val intent = intent
        var urlNews = intent.getStringExtra("url")
        try {
            web_webView.webViewClient = WebViewClient()
            web_webView.settings.loadsImagesAutomatically = true
            web_webView.settings.databaseEnabled = true
            web_webView.settings.javaScriptEnabled = true
            web_webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            if (urlNews != null) {
                web_webView.loadUrl(urlNews)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Website invalid", Toast.LENGTH_SHORT).show()
        }

        web_ivClose.setOnClickListener { finish() }
    }

    override fun onBackPressed() {
        if(web_webView.canGoBack()){
            web_webView.goBack()
        }else{
            super.onBackPressed()
        }
    }
}