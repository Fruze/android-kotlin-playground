package id.lkand.kotlinplayground.provider

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal abstract class NetworkProvider {
    companion object {
        const val BASE_URL = "https://dcc09323-5595-4e4e-b4ab-911cd88553cf.mock.pstmn.io"

        inline fun <reified T> request(logging: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE): T {
            val client = OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(HttpLoggingInterceptor().setLevel(logging))
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(T::class.java)
        }
    }
}