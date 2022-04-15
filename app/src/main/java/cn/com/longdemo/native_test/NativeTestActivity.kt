package cn.com.longdemo.native_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import cn.com.longdemo.MainActivity
import cn.com.longdemo.R
import cn.com.longdemo.databinding.ActivityNativeTestBinding
import cn.com.native_lib.NativeLib
import com.google.android.material.snackbar.Snackbar

class NativeTestActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNativeTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNativeTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_native_test)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            // native test
            NativeLib().startDownload {
                Snackbar.make(view, "callback from native", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }


        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_native_test)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}