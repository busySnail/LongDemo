package cn.com.longdemo.coroutine

import cn.com.longdemo.base.BaseSingleton

class PersonManager private constructor(name: String) {

    companion object : BaseSingleton<String, PersonManager>() {
        override fun creator(param: String): PersonManager = PersonManager(param)
    }

    fun eat(){

    }
}