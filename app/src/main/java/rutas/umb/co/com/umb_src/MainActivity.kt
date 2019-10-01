package rutas.umb.co.com.umb_src

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myWebView = findViewById<WebView>(R.id.container_web_view)
        myWebView.loadUrl("https://www.google.com")
    }
}
