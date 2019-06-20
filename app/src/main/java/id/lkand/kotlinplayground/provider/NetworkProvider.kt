package id.lkand.kotlinplayground.provider

import android.util.Log
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProvider {
    companion object {
        const val BASE_URL = "https://dcc09323-5595-4e4e-b4ab-911cd88553cf.mock.pstmn.io"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inline fun <reified T> call(): T {
        return retrofit.create(T::class.java)
    }

}