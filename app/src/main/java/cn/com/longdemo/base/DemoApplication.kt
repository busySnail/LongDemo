package cn.com.longdemo.base

import android.app.Application
import android.util.Log

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("TestML", "app-debug-onCreate: ")
    }
}