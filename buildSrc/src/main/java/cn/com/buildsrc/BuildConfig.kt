package cn.com.buildsrc

import org.apache.tools.ant.util.JavaEnvUtils.VERSION_11

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/3
 *     desc  : 配置和 Build 相关的
 * </pre>
 */
object BuildConfig {
    const val compileSdkVersion = 32

    // const val buildToolsVersion = "29.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 10001
    const val versionName = "1.0.1"
    const val JavaVersion = VERSION_11
}