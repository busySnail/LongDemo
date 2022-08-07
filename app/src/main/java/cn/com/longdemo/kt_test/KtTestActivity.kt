package cn.com.longdemo.kt_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.com.longdemo.R
import cn.com.longdemo.ktx.bindView
import coil.load
import coil.transform.CircleCropTransformation
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class KtTestActivity : AppCompatActivity(),
    AnalyticsLogger by AnalyticsLoggerImpl(),
    DeepLinkHelper by DeepLinkHelperImpl() {

    private val ivImage by bindView<ImageView>(R.id.iv_test)
    private val btnLoadImage by bindView<Button>(R.id.btn_load_img)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_test)

        registerLifecycleOwner(this)

        val textview = findViewById<TextView>(R.id.tv_delegate_test)

        val message: String? by textview


        //  message = "testxxxx"
        textview.text = "delegate"
        Log.d("TestML", "onCreate:delegate  message: $message")


        val universalDB = UniversalDB(OracleDB())
        universalDB.save()

        btnLoadImage.setOnClickListener {
            ivImage.load("https://img-blog.csdnimg.cn/20210124002108308.png") {
                crossfade(true)
                placeholder(com.google.android.material.R.drawable.abc_ic_menu_paste_mtrl_am_alpha)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeepLink(this, intent)
    }

    private operator fun TextView.provideDelegate(value: Any?, property: KProperty<*>) =
        object : ReadWriteProperty<Any?, String?> {
            override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
                return text as String?
            }

            override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
                text = value
            }

        }

}







