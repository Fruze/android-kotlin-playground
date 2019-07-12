package id.lkand.kotlinplayground.activity.dashboard.api

import id.lkand.kotlinplayground.activity.dashboard.model.DashboardModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

internal interface DashboardTarget {
    @GET("general")
    fun getGeneral(): Single<DashboardModel>

    @POST("general")
    fun postGeneral(): Single<DashboardModel>
}