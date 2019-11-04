package com.co.universidad.rutas

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebChromeClient
import com.co.universidad.rutas.utilities.ManagePermissions
import android.webkit.GeolocationPermissions
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rutas.umb.co.com.umb_src.R.layout.activity_main)
        val myWebView = findViewById<WebView>(rutas.umb.co.com.umb_src.R.id.container_web_view)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.javaScriptCanOpenWindowsAutomatically= true
        myWebView.settings.setSupportZoom(false)
        myWebView.settings.displayZoomControls = false
        myWebView.settings.builtInZoomControls = false
        myWebView.settings.setGeolocationEnabled(true)
        myWebView.webChromeClient = WebChromeClientSystem()
        myWebView.webViewClient = WebViewClientSystem()
        myWebView.loadUrl("https://rutas-universidad.firebaseapp.com")
        this.getPermission()
    }

    private fun getPermission() {
        val permission = arrayListOf<String>()
        permission.add((android.Manifest.permission.ACCESS_FINE_LOCATION))
        permission.add((android.Manifest.permission.ACCESS_COARSE_LOCATION))
        permission.add((android.Manifest.permission.INTERNET))
        ManagePermissions(this, permission, 0).requestPermissions()
    }
}

class WebViewClientSystem : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}

class WebChromeClientSystem : WebChromeClient() {
    override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
        callback.invoke(origin, true, false)
    }
}