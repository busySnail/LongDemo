package cn.com.longdemo.kt_test

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.com.longdemo.R
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class KtTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_test)

        val textview = findViewById<TextView>(R.id.tv_delegate_test)

        var message: String? by textview


        //  message = "testxxxx"
        textview.text = "delegate"
        Log.d("TestML", "onCreate:delegate  message: $message")


        val universalDB = UniversalDB(OracleDB())
        universalDB.save()

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