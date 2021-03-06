package cn.com.longdemo.ktx.playground.listener

fun main() {
    val task = Task()
    task.addTaskListener {
        onStart {

        }

        onFinish {

        }
    }

}



class Task {

    private val taskListeners: MutableList<TaskListener> = ArrayList() //监听器

    fun addTaskListener(function: TaskListenerBuilder.() -> Unit) {
        taskListeners.add(TaskListenerBuilder().also(function))
    }

}

interface TaskListener {
    fun onStart(task: Task)
    fun onRunning(task: Task)
    fun onFinish(task: Task)
    fun onRelease(task: Task)
}

private typealias onStart = (task: Task) -> Unit
private typealias onRunning = (task: Task) -> Unit
private typealias OnFinish = (task: Task) -> Unit
private typealias onRelease = (task: Task) -> Unit

class TaskListenerBuilder : TaskListener {

    private var onStart: onStart? = null
    private var onRunning: onRunning? = null
    private var onFinish: OnFinish? = null
    private var onRelease: onRelease? = null

    override fun onStart(task: Task) {
        onStart?.invoke(task)
    }

    override fun onRunning(task: Task) {
        onRunning?.invoke(task)
    }

    override fun onFinish(task: Task) {
        onFinish?.invoke(task)
    }

    override fun onRelease(task: Task) {
        onRelease?.invoke(task)
    }


    fun onStart(onStart: onStart) {
        this.onStart = onStart
    }

    fun onRunning(onRunning: onRunning) {
        this.onRunning = onRunning
    }

    fun onFinish(onFinish: OnFinish) {
        this.onFinish = onFinish
    }

    fun onRelease(onRelease: onRelease) {
        this.onRelease = onRelease
    }
}