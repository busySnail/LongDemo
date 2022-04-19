package cn.com.longdemo.stocks.test

import android.text.TextUtils
import cn.com.longdemo.base.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class StocksRepositoryImpl @Inject constructor() : StocksRepository {

    override suspend fun fetchNewStock(date:String): Flow<Resource<List<NewStock?>>> {
        return flow {
            try {
                val response = NetworkManager.stocksService.fetchNewStockList(date)
                val newStockList = getJRSGStockList(response.body().toString())
                emit(Resource.Success(newStockList))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }


    /**
     * 获取今日申购的列表
     */
    private fun getJRSGStockList(json: String): List<NewStock> {
        val resultList: MutableList<NewStock> = ArrayList<NewStock>()
        if (!TextUtils.isEmpty(json)) {
            try {
                val jsonObject = JSONObject(json)
                val resultObject = jsonObject.optJSONObject("result")
                if (resultObject != null) {
                    val dataObject = resultObject.optJSONObject("data")
                    if (dataObject != null) {
                        val jrsgTitle = NewStock().apply { type = 10 }
                        val jrsgList: List<NewStock>? = getNewStockList(dataObject, "jrsg")

                        val jrshTitle = NewStock().apply { type = 11 }
                        val jrshList: List<NewStock>? = getNewStockList(dataObject, "jrsh")

                        val jrzqTitle = NewStock().apply { type = 12 }
                        val jrzqList: List<NewStock>? = getNewStockList(dataObject, "jrzq")

                        resultList.add(jrsgTitle)
                        resultList.addAll(jrsgList ?: emptyList())
                        resultList.add(jrshTitle)
                        resultList.addAll(jrshList ?: emptyList())
                        resultList.add(jrzqTitle)
                        resultList.addAll(jrzqList ?: emptyList())
                        return resultList
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        return resultList
    }

    private fun getNewStockList(jsonObject: JSONObject, key: String): List<NewStock>? {
        val stockList: MutableList<NewStock> = java.util.ArrayList()
        val jsonArray = jsonObject.optJSONArray(key)
        if (jsonArray != null) {
            for (i in 0 until jsonArray.length()) {
                val `object` = jsonArray.optJSONObject(i)
                if (`object` != null) {
                    val stock = NewStock()

                    //新股申购字段
                    stock.companyCode = getFiledValue(`object`, "CompanyCode")
                    stock.paperName = getFiledValue(`object`, "PaperName")
                    stock.paperCode = getFiledValue(`object`, "PaperCode")
                    stock.bourse = getFiledValue(`object`, "Bourse")
                    stock.stockType = getFiledValue(`object`, "Type")
                    stock.netCode = getFiledValue(`object`, "NetCode")
                    stock.issuePrice = getFiledValue(`object`, "IssuePrice")
                    stock.futurePrice = getFiledValue(`object`, "FuturePrice")
                    stock.inComeRate = getFiledValue(`object`, "InComeRate")
                    stock.upperLimit = getFiledValue(`object`, "UpperLimit")
                    stock.zql = getFiledValue(`object`, "Zql")
                    stock.marketDate = getFiledValue(`object`, "MarketDate")
                    stock.netDate = getFiledValue(`object`, "NetDate")
                    stock.netAnnoDate = getFiledValue(`object`, "NetAnnoDate")
                    stock.issueNum = getFiledValue(`object`, "IssueNum")
                    stock.netNum = getFiledValue(`object`, "NetNum")
                    stock.upperLimitAmount = getFiledValue(`object`, "UpperLimitAmount")
                    stock.lowerLimit = getFiledValue(`object`, "LowerLimit")
                    stock.offlineNetAnnoDate = getFiledValue(`object`, "OfflineNetAnnoDate")

                    //新债申购字段
                    stock.seCode = getFiledValue(`object`, "SeCode")
                    stock.bondCode = getFiledValue(`object`, "BondCode")
                    stock.bondName = getFiledValue(`object`, "BondName")
                    stock.paperPrice = getFiledValue(`object`, "PaperPrice")
                    stock.zqh = getFiledValue(`object`, "Zqh")
                    when (key) {
                        "jrsg" -> {
                            stock.type = 0
                        }
                        "jrsh" -> {
                            stock.type = 1
                        }
                        "jrzq" -> {
                            stock.type = 2
                        }
                    }
                    if (i == jsonArray.length() - 1) {
                        stock.isLast = true
                    }
                    stockList.add(stock)
                }
            }
        }
        return stockList
    }

    private fun getFiledValue(jsonObject: JSONObject, key: String): String? {
        return if (jsonObject.has(key)) {
            jsonObject.optString(key)
        } else {
            ""
        }
    }


}