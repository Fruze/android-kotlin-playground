package id.lkand.kotlinplayground.feature.dashboard.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lkand.kotlinplayground.feature.dashboard.api.DashboardTarget
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class DashboardViewModel: ViewModel() {
    val dashboardModel = MutableLiveData<DashboardModel>()

    internal data class Input(
        val didLoad: Observable<Boolean>,
        val getTrigger: Observable<Boolean>,
        val postTrigger: Observable<Boolean>
    )

    @SuppressLint("CheckResult")
    internal fun transform(input: Input): MutableLiveData<DashboardModel> {
        Observable.merge(input.didLoad, input.getTrigger)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>()
                    .getGeneral()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            }

        input.postTrigger
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>()
                    .postGeneral()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            }

        return this.dashboardModel
    }

}

