package cn.com.longdemo.ktx

/**
 * JDK相关
 */

fun <T : Any, R : Any> whenAllNotNull(vararg options: T?, block: (List<T>) -> R) {
    if (options.all { it != null }) {
        block(options.filterNotNull()) // or do unsafe cast to non null collection
    }
}

fun whenAllNullOrEmpty(
    vararg options: String?, block: () -> Unit
) {
    if (options.all { it.isNullOrEmpty() }) {
        block()
    }
}