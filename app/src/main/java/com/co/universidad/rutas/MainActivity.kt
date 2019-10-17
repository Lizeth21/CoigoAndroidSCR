package com.co.universidad.rutas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import rutas.umb.co.com.umb_src.R
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import sun.jvm.hotspot.utilities.IntArray
import android.R



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myWebView = findViewById<WebView>(R.id.container_web_view)
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        myWebView.webChromeClient = object : WebChromeClient() {
        }
        myWebView.loadUrl("https://rutas-universidad.firebaseapp.com")
    }
}
