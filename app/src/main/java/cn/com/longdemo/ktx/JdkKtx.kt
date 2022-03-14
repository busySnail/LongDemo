package cn.com.longdemo.ktx

import androidx.lifecycle.MutableLiveData

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

operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
    val value = this.value ?: arrayListOf()
    value.addAll(values)
    this.value = value
}

operator fun <T> MutableLiveData<ArrayList<T>>.minusAssign(item: T) {
    val value = this.value ?: arrayListOf()
    value.remove(item)
    this.value = value
}