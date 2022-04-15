package cn.com.longdemo.js_test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JsTestViewModel : ViewModel() {
    private val _counterLivaData = MutableLiveData<Int>()
    val counterLivaData: LiveData<Int> get() = _counterLivaData
    var job: Job? = null

    val isCounting = MutableLiveData<Boolean>()


    fun startCounter() {
        job = viewModelScope.launch {
            isCounting.value = true
            start()
        }
    }

    fun stopCounter() {
        isCounting.value = false
        job?.cancel()
    }

    private suspend fun start() {
        repeat(1000) {
            val now = _counterLivaData.value ?: 0
            _counterLivaData.value = now.plus(1)
            delay(1000)
        }
    }

}