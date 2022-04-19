package cn.com.longdemo.stocks.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.com.longdemo.base.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {


    private var repository: StocksRepository = StocksRepositoryImpl()

    private val _newStockLiveData = MutableLiveData<Resource<List<NewStock?>>>()
    val newStockLiveData: LiveData<Resource<List<NewStock?>>> get() = _newStockLiveData

    fun fetchNewStocks(date:String) {
        _newStockLiveData.value = Resource.Loading()
        viewModelScope.launch {
            repository.fetchNewStock(date).collectLatest {
                _newStockLiveData.value = it
            }
        }

    }


}