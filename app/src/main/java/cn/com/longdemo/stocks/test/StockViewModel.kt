package cn.com.longdemo.stocks.test

import androidx.lifecycle.*
import cn.com.longdemo.base.Resource
import cn.com.longdemo.ktx.SingleEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {


    private var repository: StocksRepository = StocksRepositoryImpl()

    private val _newStockLiveData = MutableLiveData<Resource<List<NewStock>>>()
    val newStockLiveData: LiveData<Resource<List<NewStock>>> get() = _newStockLiveData

    private val _openStockDetailsPrivate = MutableLiveData<SingleEvent<NewStock>>()
    val openStockDetails: LiveData<SingleEvent<NewStock>> get() = _openStockDetailsPrivate

     val openStateFlow = MutableStateFlow("")

    fun fetchNewStocks(date: String) {
        _newStockLiveData.value = Resource.Loading()
        viewModelScope.launch {
            repository.fetchNewStock(date).collectLatest {
                _newStockLiveData.value = it
            }
        }
    }

    fun fetchNewStocks2(date: String) = liveData {
        _newStockLiveData.value = Resource.Loading()
        viewModelScope.launch {
            repository.fetchNewStock(date).collectLatest {
                //_newStockLiveData.value = it
                emit(it)
            }
        }
    }

    fun openStockDetail(stock: NewStock) {
        openStateFlow.value= stock.paperName.toString()
        _openStockDetailsPrivate.value = SingleEvent(stock)
    }


}