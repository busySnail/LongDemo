package cn.com.longdemo.js_test

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.com.longdemo.databinding.JsTestActivityBinding
import com.google.android.material.snackbar.Snackbar
import com.gzsll.jsbridge.WVJBWebView
import com.gzsll.jsbridge.WVJBWebViewClient


class JsTestActivity : AppCompatActivity() {
    private lateinit var binding: JsTestActivityBinding
    private val viewModel: JsTestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JsTestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.counterLivaData.observe(this) {
            binding.timer.text = "计数器:$it"
        }

        viewModel.isCounting.observe(this, Observer {

        })

        binding.webView.apply {
            loadUrl("file:///android_asset/ExampleApp.html")
            webViewClient = CustomWebViewClient(this)

            registerHandler("testJavaCallback") { data, callback ->
                Snackbar.make(this, "从Js调用Java", Snackbar.LENGTH_SHORT).setAction("action") {

                }.show()
            }

            registerHandler("startTimer") { data, callback ->
                viewModel.startCounter()
            }

            registerHandler("stopTimer") { data, callback ->
                viewModel.stopCounter()
            }
        }

        binding.btn.setOnClickListener {
            binding.webView.callHandler(
                "testJavascriptHandler", "{\"greetingFromJava\": \"Hi there, JS!\" }"
            ) { data ->
                //callback被js回调
                Snackbar.make(binding.webView, "Js回调Java的callback", Snackbar.LENGTH_SHORT)
                    .setAction("action") {

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