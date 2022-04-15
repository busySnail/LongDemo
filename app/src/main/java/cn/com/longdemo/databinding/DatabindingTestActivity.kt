package cn.com.longdemo.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField

class DatabindingTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DatabindingTestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userInfo = PersonModel("", "")
    }

    class PersonModel(name: String, age: String) {
        var nameOb: ObservableField<String>? = null
        var ageOb: ObservableField<String>? = null

        init {
            nameOb = ObservableField(name)
            ageOb = ObservableField(age)
        }
    }

}