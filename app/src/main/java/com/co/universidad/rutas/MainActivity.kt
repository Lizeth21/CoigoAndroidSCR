package com.co.universidad.rutas

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebChromeClient
import com.co.universidad.rutas.utilities.ManagePermissions
import rutas.umb.co.com.umb_src.R
import android.webkit.GeolocationPermissions
import android.webkit.WebViewClient
import android.support.v7.app.AlertDialog


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myWebView = findViewById<WebView>(R.id.container_web_view)
        myWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        myWebView.settings.builtInZoomControls = true
        myWebView.webViewClient = GeoWebViewClient()
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.setGeolocationEnabled(true)
        myWebView.webChromeClient = GeoWebChromeClient(this)
        myWebView.loadUrl("https://rutas-universidad.firebaseapp.com")
        this.getPermission()
    }

    private fun getPermission() {
        val permission = arrayListOf<String>()
        permission.add((android.Manifest.permission.ACCESS_FINE_LOCATION))
        permission.add((android.Manifest.permission.ACCESS_COARSE_LOCATION))
        ManagePermissions(this, permission, 0).requestPermissions()
    }
}

class GeoWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}

/**
 * WebChromeClient subclass handles UI-related calls
 * Note: think chrome as in decoration, not the Chrome browser
 */
class GeoWebChromeClient(private val context: Context) : WebChromeClient() {

    override fun onGeolocationPermissionsShowPrompt(
        origin: String,
        callback: GeolocationPermissions.Callback
    ) {
        val remember = false
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Locations")
        builder.setMessage("Would like to use your Current Location ")
            .setCancelable(true).setPositiveButton("Allow"
            ) { _, _ ->
                // origin, allow, remember
                callback.invoke(origin, true, remember)
            }.setNegativeButton("Don't Allow"
            ) { _, _ ->
                // origin, allow, remember
                callback.invoke(origin, false, remember)
            }
        val alert = builder.create()
        alert.show()
    }
}