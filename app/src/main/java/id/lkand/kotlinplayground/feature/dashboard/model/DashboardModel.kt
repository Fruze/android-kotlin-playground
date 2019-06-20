package id.lkand.kotlinplayground.feature.dashboard.model

data class DashboardModel(
    val code: String,
    val content: DashboardContentModel
)

data class DashboardContentModel(
    val status: Int,
    val description: String,
    val method: String
)