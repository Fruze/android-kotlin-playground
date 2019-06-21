package id.lkand.kotlinplayground.feature.dashboard.viewmodel

import android.util.Log
import id.lkand.kotlinplayground.feature.dashboard.api.DashboardTarget
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class DashboardViewModel {

    internal fun transform() {
        NetworkProvider.run {
            request<DashboardTarget>()
                .getGeneral()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = { response: DashboardModel ->
                    Log.d("Retrofit success", response.Content.Method)
                }, onError = { error ->
                    Log.e("Retrofit error", error.message)
                })
        }

        NetworkProvider.run {
            request<DashboardTarget>()
                .postGeneral()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = { response ->
                    Log.d("Retrofit success", response.Content.Method)
                }, onError = { error ->
                    Log.e("Retrofit error", error.message)
                })
        }
    }

    // TODO: Modeling

}

