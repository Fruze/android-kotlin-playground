package id.lkand.kotlinplayground.activity.dashboard.api

import id.lkand.kotlinplayground.activity.dashboard.model.DashboardModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

internal interface DashboardTarget {
    @GET("general")
    fun getGeneral(): Observable<DashboardModel>

    @POST("general")
    fun postGeneral(): Observable<DashboardModel>
}