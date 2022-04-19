package cn.com.longdemo.stocks.test

/**
 * @author hongda5
 * @date 2020/10/22
 */
data class NewStock(
    //新股申购使用字段
    var companyCode: String? = null,
    var paperName: String? = null,
    var paperCode: String? = null,
    var bourse: String? = null,
    var netCode: String? = null,
    var issuePrice: String? = null,
    var futurePrice: String? = null,
    var inComeRate: String? = null,
    var upperLimit: String? = null,
    var zql: String? = null,
    var marketDate: String? = null,
    var netDate: String? = null,
    var netAnnoDate: String? = null,
    var issueNum: String? = null,
    var netNum: String? = null,
    var upperLimitAmount: String? = null,
    var lowerLimit: String? = null,
    var offlineNetAnnoDate: String? = null,
    var stockType: String? = null,
    var industry: String? = null,
    var industryID: String? = null,

    //新债申购使用字段
    var seCode: String? = null,
    var bondCode: String? = null,
    var bondName: String? = null,
    var dQZGPrice: String? = null,
    var yJL: String? = null,
    var paperPrice: String? = null,
    var zqh: String? = null,
    //新股/新三板详情使用字段
    /**
     * 公司简介
     */
    var description: String? = null,

    /**
     * 经营范围
     */
    var businessScope: String? = null,

    //本地变量，用来标识类型；
    // 0今日申购 1今日上市 2今日中签
    //10今日申购title 11今日上市title 12今日中签title
    var type: Int? = 0,

    //是否是最后一个数据
    var isLast: Boolean? = false,

    //转股溢价率
    var overRatio: Float? = 0f,

    )