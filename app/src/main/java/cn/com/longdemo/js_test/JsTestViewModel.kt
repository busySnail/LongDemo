package cn.com.longdemo.js_test

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class JsTestViewModel : ViewModel() {
    private val _counterLivaData = MutableLiveData<Int>()
    val counterLivaData: LiveData<Int> get() = _counterLivaData
    var job: Job? = null

    private lateinit var flow: Flow<Int>

    val isCounting = MutableLiveData<Boolean>()


    fun startCounter() {
        job = viewModelScope.launch {
            isCounting.value = true
            start()
        }


        setUpFlow()


    }

    fun stopCounter() {
        isCounting.value = false
        job?.cancel()


        CoroutineScope(Dispatchers.Main).launch {
            flow.collect {
                Log.d("TestML", "startCounter:collect  $it: ${Thread.currentThread().name}")
            }
        }
    }

    private suspend fun start() {
        repeat(1000) {
            val now = _counterLivaData.value ?: 0
            _counterLivaData.value = now.plus(1)
            delay(1000)
        }
    }

    private fun setUpFlow() {

        flow = flow {
            Log.d("TestML", "setUpFlow: start ")
            (1..10).forEach {
                delay(500)
                Log.d("TestML", "setUpFlow: $it ${Thread.currentThread().name}")
                emit(it)
            }
        }.map {
            it * it
        }.flowOn(Dispatchers.Default)
    }
}