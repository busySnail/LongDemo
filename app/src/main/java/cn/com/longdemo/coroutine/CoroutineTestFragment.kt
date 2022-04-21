package cn.com.longdemo.coroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cn.com.longdemo.databinding.FragmentFirst3Binding
import cn.com.longdemo.ktx.throttleClick
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CoroutineTestFragment : Fragment() {

    var increaseNum = 0
    private var _binding: FragmentFirst3Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirst3Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAsyncTest.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                val name = async { getName() }
                val age = async { getAge() }
                binding.tvAsyncTest.text = "获取name:${name.await()} 获取age:${age.await()}"
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            binding.svSearchTest.getQueryTextChangeStateFlow().debounce(300).filter { query ->
                query.isNotEmpty()
            }.flatMapLatest { query ->
                dataFromNetwork(query).catch {
                    emitAll(flowOf(""))
                }
            }.flowOn(Dispatchers.Main).collect { result ->
                binding.tvSearchTest.text = "模拟 $result 的搜索结果..."
            }
        }

        binding.btnThrottleTestNormal.setOnClickListener {
            increaseNum()
        }

        binding.btnThrottleTest.throttleClick {
            increaseNum()
        }

    }

    private fun increaseNum() {
        binding.tvThrottleTest.text = (increaseNum++).toString()
    }

    private suspend fun getName(): String = withContext(Dispatchers.IO) {
        delay(3000)
        return@withContext "Bob"
    }

    private suspend fun getAge(): Int = withContext(Dispatchers.IO) {
        delay(6000)
        return@withContext 30
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataFromNetwork(query: String): Flow<String> {
        return flow {
            delay(2000)
            emit(query)
        }
    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

        val query = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })

        return query

    }
}