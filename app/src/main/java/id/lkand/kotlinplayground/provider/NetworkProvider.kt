package id.lkand.kotlinplayground.provider

import id.lkand.kotlinplayground.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal abstract class NetworkProvider {
    companion object {
        inline fun <reified T> request(logging: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE): T {
            val interceptor = {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = logging
                interceptor
            }()

            val client = OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.URL_DEV)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(T::class.java)
        }
    }
}