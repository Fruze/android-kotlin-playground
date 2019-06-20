package id.lkand.kotlinplayground.provider

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class NetworkProvider {
    companion object {
        const val BASE_URL = "https://dcc09323-5595-4e4e-b4ab-911cd88553cf.mock.pstmn.io"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        inline fun <reified T> request(): T {
            return retrofit.create(T::class.java)
        }
    }
}