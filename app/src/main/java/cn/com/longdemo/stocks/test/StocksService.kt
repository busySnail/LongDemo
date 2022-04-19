package cn.com.longdemo.stocks.test

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksService {

    @GET("cn/api/openapi.php/NewStockService.getJRSG")
    suspend fun fetchNewStockList(@Query("date") date: String = "2022-04-18"): Response<Any?>

}