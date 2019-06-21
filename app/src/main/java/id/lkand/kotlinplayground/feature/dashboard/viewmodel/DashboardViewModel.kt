package id.lkand.kotlinplayground.feature.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lkand.kotlinplayground.feature.dashboard.api.DashboardTarget
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class DashboardViewModel: ViewModel() {

    internal val dashboardModel = MutableLiveData<DashboardModel>()

    internal fun transform() {
        NetworkProvider.run {
            request<DashboardTarget>()
                .getGeneral()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = { response: DashboardModel ->
                    this@DashboardViewModel.dashboardModel.value = response
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
                    // Stub
                }, onError = { error ->
                    Log.e("Retrofit error", error.message)
                })
        }
    }

    // TODO: Modeling

}

