package id.lkand.kotlinplayground.feature.dashboard.repository

import android.util.Log
import id.lkand.kotlinplayground.`interface`.Repository
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Callback
import retrofit2.Response

interface IDashboardRepository {
    @GET("general")
    fun getGeneral(): Call<DashboardModel>
}

class DashboardRepository : Repository {

    fun getGeneral() {
        return NetworkProvider
            .request<IDashboardRepository>()
            .getGeneral()
            .enqueue(object : Callback<DashboardModel> {
                override fun onResponse(call: Call<DashboardModel>, response: Response<DashboardModel>) {
                    // TODO: Coroutines
                    val KontakList = response.body().Content.Description
                    Log.d("Retrofit Get", KontakList)
                }

                override fun onFailure(call: Call<DashboardModel>, t: Throwable) {
                    Log.e(call.toString(), t.toString())
                }
            })
    }

}