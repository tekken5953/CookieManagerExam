package app.cookiemanagerexam

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val cookieManager by lazy {CookieManager.getInstance()}

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)

        webView.apply {
            this.webChromeClient = WebChromeClient()
            this.webViewClient = WebViewClient()
        }

        webView.settings.apply {
            this.javaScriptEnabled = true
            this.useWideViewPort = true
        }

        cookieManager.apply {
            this.acceptCookie()
            this.acceptThirdPartyCookies(webView)
        }
    }

    private fun hasCookies(): Boolean {
        return cookieManager.hasCookies()
    }

    private fun setCookie(key: String?, value: String?) {
        cookieManager.setCookie(key,value)
    }

    private fun getCookie(key: String?): String? {
        return cookieManager.getCookie(key)
    }

    private fun clearCookie() {
        val callback = ValueCallback<Boolean> {
            println(if(it) "success to remove" else "fail to remove")
        }
        cookieManager.removeAllCookies(callback)
    }
}