package cn.com.longdemo.js_test

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cn.com.longdemo.R
import cn.com.longdemo.ktx.bindView
import com.google.android.material.snackbar.Snackbar
import com.gzsll.jsbridge.WVJBWebView
import com.gzsll.jsbridge.WVJBWebView.WVJBResponseCallback
import com.gzsll.jsbridge.WVJBWebViewClient


class JsTestActivity : AppCompatActivity() {
    private val btn by bindView<Button>(R.id.btn)
    private val webView by bindView<WVJBWebView>(R.id.webView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.js_test_activity)
        webView.apply {
            loadUrl("file:///android_asset/ExampleApp.html")
            webViewClient = CustomWebViewClient(this)

            registerHandler("testJavaCallback") { data, callback ->
                Snackbar.make(this, "从Js调用Java", Snackbar.LENGTH_SHORT).setAction("action") {

                }.show()
            }
        }

        btn.setOnClickListener {
            webView.callHandler("testJavascriptHandler",
                "{\"greetingFromJava\": \"Hi there, JS!\" }"
            ) { data ->
                //callback被js回调
                Snackbar.make(webView, "Js回调Java的callback", Snackbar.LENGTH_SHORT).setAction("action") {

                }.show()
            }
        }


    }

    class CustomWebViewClient(webView: WVJBWebView?) : WVJBWebViewClient(webView) {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            //  do your work here
            // ...
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}