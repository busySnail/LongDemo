package cn.com.longdemo.stocks.test

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StocksResponse(
    val result: Result? = null
) : Parcelable

@Parcelize
data class Data(
    val jrsg: List<JrsgItem?>? = null,
    val jrzq: List<JrzqItem?>? = null
) : Parcelable

@Parcelize
data class JrsgItem(
    @SerializedName("paperName")
    val paperName: String? = null,
    val industryID: String? = null,
    val offlineNetAnnoDate: String? = null,
    val upperLimitAmount: String? = null,
    val futurePrice: String? = null,
    val netCode: String? = null,
    val marketDate: String? = null,
    val netDate: String? = null,
    val netAnnoDate: String? = null,
    val upperLimit: String? = null,
    val bourse: String? = null,
    val industry: String? = null,
    val issueNum: String? = null,
    val type: String? = null,
    val zql: String? = null,
    val issuePrice: String? = null,
    val netNum: String? = null,
    val paperCode: String? = null,
    val inComeRate: String? = null,
    val lowerLimit: String? = null,
    val companyCode: String? = null
) : Parcelable

@Parcelize
data class JrzqItem(
    val paperName: String? = null,
    val industryID: String? = null,
    val offlineNetAnnoDate: String? = null,
    val upperLimitAmount: String? = null,
    val futurePrice: String? = null,
    val netCode: String? = null,
    val marketDate: String? = null,
    val netDate: String? = null,
    val netAnnoDate: String? = null,
    val upperLimit: String? = null,
    val bourse: String? = null,
    val industry: String? = null,
    val issueNum: String? = null,
    val type: String? = null,
    val zql: String? = null,
    val issuePrice: String? = null,
    val netNum: String? = null,
    val paperCode: String? = null,
    val inComeRate: String? = null,
    val lowerLimit: String? = null,
    val companyCode: String? = null
) : Parcelable

@Parcelize
data class Status(
    val code: Int? = null
) : Parcelable

@Parcelize
data class Result(
    val data: Data? = null, val status: Status? = null
) : Parcelable

