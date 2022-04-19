package cn.com.longdemo.stocks.new_stock

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import cn.com.longdemo.R
import cn.com.longdemo.base.Result
import cn.com.longdemo.base.doFailure
import cn.com.longdemo.base.doSuccess
import cn.com.longdemo.databinding.ActivityNewStockBinding
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewStockActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNewStockBinding

//    @Inject
//    private lateinit var data: TestData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_new_stock)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->


            lifecycleScope.launch {
                fetchNewStock().onStart {
                    Log.d("TestML", "onStart: ")

                }.onCompletion {
                    Log.d("TestML", "onCompletion: ")

                }.collectLatest {
                    it.doSuccess {
                        Log.d("TestML", "doSuccess: $it ")
                    }

                    it.doFailure {
                        Log.d("TestML", "doFailure: $it ")

                    }
                }

            }


        }
    }

    fun fetchNewStock(): Flow<Result<Any?>> {
        return flow {
            try {
                val response = NetworkManager.stocksService.fetchNewStockList()
                emit(Result.Success(response.body()))
            } catch (e: Exception) {
                emit(Result.Failure(e.cause))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_new_stock)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}