package cn.com.longdemo.base


sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()

    data class Failure(val throwable: Throwable?) : Result<Nothing>()
}

inline fun <reified T> Result<T>.doSuccess(success: (T) -> Unit) {
    if (this is Result.Success) {
        success(value)
    }
}

inline fun <reified T> Result<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is Result.Failure) {
        failure(throwable)
    }
}
