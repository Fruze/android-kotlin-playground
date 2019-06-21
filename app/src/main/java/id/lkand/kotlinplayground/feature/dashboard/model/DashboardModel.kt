package id.lkand.kotlinplayground.feature.dashboard.model

import com.google.gson.annotations.SerializedName

internal data class DashboardModel(
    @SerializedName("code")
    val Code: String,

    @SerializedName("content")
    val Content: DashboardContentModel
)

internal data class DashboardContentModel(
    @SerializedName("status")
    val Status: Int,

    @SerializedName("description")
    val Description: String,

    @SerializedName("method")
    val Method: String
)