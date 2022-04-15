package cn.com.longdemo

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.com.longdemo.annotations.HideInMainActivity
import cn.com.longdemo.ktx.bindView
import cn.com.longdemo.ktx.plusAssign
import cn.com.native_lib.NativeLib
import com.aesean.activitystack.view.recyclerview.ListAdapter


@HideInMainActivity
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "TestML"
    }

    private val recyclerView by bindView<RecyclerView>(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datas = MutableLiveData<ArrayList<String>>()
        datas.value = arrayListOf("xx", "aa")

        datas += mutableListOf("ee")

        Log.d("TestML", "onCreate: datas: ${datas.value}")

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity, DividerItemDecoration.VERTICAL
                )
            )
            itemAnimator = DefaultItemAnimator()
        }

        val listAdapter = ListAdapter()
        recyclerView.adapter = listAdapter

        listAdapter.register(ActivityInfo::class.java).layoutRes { R.layout.view_holder_main }
            .onViewCreated { view, viewHolder, dataHolder ->
                view.setOnClickListener {
                    val data = dataHolder()
                    val targetName = data.name
                    val packageName = data.packageName
                    val intent = Intent()
                    intent.`package` = packageName
                    intent.setClassName(packageName, targetName)
                    try {
                        view.context.startActivity(intent)
                    } catch (e: Exception) {

                    }
                }
            }.onBindView { view, data ->
                view.findViewById<TextView>(R.id.titleView).text = data.title
                view.findViewById<TextView>(R.id.contentView).text =
                    data.name.replace(data.packageName, "")
            }

        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        val list = packageInfo.activities.filter { shouldShow(it.name) }.sortedBy { it.title }
        listAdapter.submitList(list)


        //Build Config
        Log.d(TAG, "onCreate: " + BuildConfig.TEST_FIELD)


    }

    private val ActivityInfo.title: String
        get() {
            val lastIndex = this.name.lastIndexOf(".")
            if (lastIndex < 0) {
                return this.name
            }
            return this.name.substring(lastIndex + 1)
        }

    private fun shouldShow(name: String?): Boolean {
        if (name.isNullOrBlank() || !name.contains(packageName)) {
            return false
        }
        val clazz = Class.forName(name)
        val annotation = clazz.getAnnotation(HideInMainActivity::class.java)
        return annotation == null
    }
}
