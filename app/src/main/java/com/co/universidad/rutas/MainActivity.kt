package com.co.universidad.rutas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import rutas.umb.co.com.umb_src.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myWebView = findViewById<WebView>(R.id.container_web_view)
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        myWebView.loadUrl("http://192.168.0.19:4200/")
    }
}
