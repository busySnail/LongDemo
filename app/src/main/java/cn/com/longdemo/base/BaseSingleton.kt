package cn.com.longdemo.base

//双重检测法实现的单例，object通过静态代码块实现，无法做到懒加载，双重检测法是懒加载的方式
abstract class BaseSingleton<in P, out T> {

    @Volatile
    private var instance: T? = null

    protected abstract fun creator(param: P): T

    fun getInstance(param: P): T = instance ?: synchronized(this) {
        instance ?: creator(param).also {
            instance = it
        }
    }

}