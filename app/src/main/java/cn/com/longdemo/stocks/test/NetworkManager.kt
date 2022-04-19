package cn.com.longdemo.stocks.test

import android.annotation.SuppressLint
import cn.com.longdemo.base.SslUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

object NetworkManager {

    lateinit var stocksService: StocksService

    fun initRetrofit() {
        val okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(SslUtils.createSSLSocketFactory(), TrustAllCerts()).build()

        val retrofit = Retrofit.Builder().client(okHttpClient).baseUrl("https://quotes.sina.cn/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        stocksService = retrofit.create(StocksService::class.java)
    }

    class TrustAllCerts : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate?> {
            return arrayOfNulls(0)
        }
    }
}