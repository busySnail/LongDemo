package cn.com.longdemo.stocks.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cn.com.longdemo.base.onFailure
import cn.com.longdemo.base.onLoading
import cn.com.longdemo.base.onSuccess
import cn.com.longdemo.databinding.FragmentFirst2Binding
import cn.com.longdemo.ktx.toGone
import cn.com.longdemo.ktx.toVisible
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstStockFragment : Fragment() {

    private val viewModel: StockViewModel by viewModels()
    private lateinit var stockAdapter: StocksAdapter

    private var _binding: FragmentFirst2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirst2Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.newStockLiveData.observe(viewLifecycleOwner) { result ->

            result.onLoading {
                Log.d("TestML", "onCreate: onLoading ")
            }

            result.onSuccess { stockList ->
                bindListData(stockList?.filter {
                    it?.paperName?.isNotEmpty() == true
                })
                stockList?.forEach {
                    Log.d("TestML", "onCreate: onSuccess: ${it?.paperName}")
                }
            }

            result.onFailure {
                Log.d("TestML", "onCreate: onFailure: $it")

            }

        }

        val df = SimpleDateFormat("yyyy-MM-dd")


        binding.fab.setOnClickListener {

            val dateStr = df.format(Date())
            viewModel.fetchNewStocks(dateStr)
            binding.titleDesc.text = "今日申购 $dateStr"
        }
    }

    private fun bindListData(stocks: List<NewStock?>?) {
        if (!(stocks.isNullOrEmpty())) {
            stockAdapter = StocksAdapter(stocks)
            binding.rvStocksList.adapter = stockAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }

    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvStocksList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvStocksList.toGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}