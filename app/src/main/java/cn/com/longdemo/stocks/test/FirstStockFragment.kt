package cn.com.longdemo.stocks.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import cn.com.longdemo.base.BaseFragment
import cn.com.longdemo.base.Resource
import cn.com.longdemo.databinding.FragmentFirst2Binding
import cn.com.longdemo.ktx.observe
import cn.com.longdemo.ktx.toGone
import cn.com.longdemo.ktx.toVisible
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstStockFragment : BaseFragment() {

    private val viewModel: StockViewModel by viewModels()
    private lateinit var stockAdapter: StocksAdapter

    private var _binding: FragmentFirst2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirst2Binding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val df = SimpleDateFormat("yyyy-MM-dd")
        binding.fab.setOnClickListener {

            val dateStr = df.format(Date())
            viewModel.fetchNewStocks(dateStr)
            binding.titleDesc.text = "今日申购 $dateStr"


        }
    }


    override fun onResume() {
        super.onResume()
        Log.d("TestML", "onResume: ")
    }

    private fun bindListData(stocks: List<NewStock>?) {
        if (!(stocks.isNullOrEmpty())) {
            stockAdapter = StocksAdapter(viewModel, stocks)
            binding.rvStocksList.adapter = stockAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }

    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvStocksList.visibility = if (show) View.VISIBLE else View.GONE
        binding.progressCircular.toGone()
    }

    private fun showLoadingView() {
        binding.progressCircular.toVisible()
        binding.tvNoData.toGone()
        binding.rvStocksList.toGone()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun observeViewModel() {
        observe(viewModel.newStockLiveData, ::handleStockList)
        //  observeEvent(viewModel.openStockDetails, ::handleOpenDetail)

        lifecycleScope.launch {
            viewModel.openStateFlow.collect {
                handleOpenDetail(it)
            }
        }


    }

    private fun handleOpenDetail(paperName: String) {
        Log.d("TestML", "handleOpenDetail: paperName:${paperName}")

    }

    private fun handleStockList(status: Resource<List<NewStock>>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let {
                bindListData(status.data.filter {
                    it.paperName?.isNotEmpty() == true
                })
            }
            else -> {

            }
        }
    }
}