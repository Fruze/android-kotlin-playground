package id.lkand.kotlinplayground.feature.dashboard.repository

import android.util.Log
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Response


interface GerritAPI {

    @GET("general")
    fun loadChanges()
}

class Controller : Callback<List<DashboardModel>> {

    fun start() {
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gerritAPI = retrofit.create(GerritAPI::class.java)

        val call = gerritAPI.loadChanges()
//        call.enqueue(this)

    }

    override fun onResponse(call: Call<List<DashboardModel>>, response: Response<List<DashboardModel>>) {
        if (response.isSuccessful()) {
            val changesList = response.body()
            changesList.forEach { code -> System.out.println("DebugUtilDashboard" + code.code) }
        } else {
            System.out.println("DebugUtil" + response.errorBody())
        }
    }

    override fun onFailure(call: Call<List<DashboardModel>>, t: Throwable) {
        t.printStackTrace()
        Log.d("DebugUtil Failed", t.toString())
    }

    companion object {
        internal val BASE_URL = "https://dcc09323-5595-4e4e-b4ab-911cd88553cf.mock.pstmn.io"
    }
}