package cn.com.longdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.com.native_lib.NativeLib

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "TestML"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Build Config
        Log.d(TAG, "onCreate: " + BuildConfig.TEST_FIELD)
        // native test
        NativeLib().startDownload {
            Log.d(TAG, "onCreate: $it")
        }

    }
}