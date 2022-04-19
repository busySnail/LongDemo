package cn.com.longdemo.stocks.test

import cn.com.longdemo.base.Resource
import kotlinx.coroutines.flow.Flow

interface StocksRepository {
   suspend fun fetchNewStock(data:String):Flow<Resource<List<NewStock?>>>
}