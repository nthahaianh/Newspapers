package com.example.newspapers.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newspapers.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        var intent = intent
        var urlNews = intent.getStringExtra("url")
        try {
            if (urlNews != null) {
                web_webView.loadUrl(urlNews)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Website invalid", Toast.LENGTH_SHORT).show()
        }

        web_ivClose.setOnClickListener { finish() }
    }
}