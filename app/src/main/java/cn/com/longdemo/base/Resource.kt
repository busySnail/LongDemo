package cn.com.longdemo.base

import android.util.Log

sealed class Resource<T>(
    val data: T? = null, val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Failure<T>(e: Throwable) : Resource<T>(null, e)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=${Log.getStackTraceString(error)}]"
            is Loading<T> -> "Loading"
        }
    }
}

inline fun <reified T> Resource<T>.onLoading(load: () -> Unit) {
    if (this is Resource.Loading) {
        load()
    }
}

inline fun <reified T> Resource<T>.onSuccess(success: (T?) -> Unit) {
    if (this is Resource.Success) {
        success(data)
    }
}

inline fun <reified T> Resource<T>.onFailure(failure: (Throwable?) -> Unit) {
    if (this is Resource.Failure) {
        failure(error)
    }
}
