package cn.com.longdemo.kt_test

import android.content.Intent
import androidx.activity.ComponentActivity

interface DeepLinkHelper {
    fun handleDeepLink(activity: ComponentActivity, intent: Intent?)
}