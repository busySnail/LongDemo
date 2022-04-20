package cn.com.longdemo.coroutine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cn.com.longdemo.databinding.FragmentFirst3Binding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CoroutineTestFragment : Fragment() {

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
            // findNavController().navigate(R.id.action_First3Fragment_to_Second3Fragment)
            Log.d("TestML", "launch")
            lifecycleScope.launch(Dispatchers.Main) {
                val name = async { getName() }
                val age = async { getAge() }
                binding.tvAsyncTest.text = "获取name:${name.await()} 获取age:${age.await()}"
            }
        }
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
}