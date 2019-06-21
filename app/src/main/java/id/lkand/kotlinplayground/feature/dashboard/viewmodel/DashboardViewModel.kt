package id.lkand.kotlinplayground.feature.dashboard.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lkand.kotlinplayground.extension.subscribeIO
import id.lkand.kotlinplayground.feature.dashboard.api.DashboardTarget
import id.lkand.kotlinplayground.feature.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

internal class DashboardViewModel : ViewModel() {
    val dashboardModel = MutableLiveData<DashboardModel>()

    internal data class Input(
        val didLoad: Observable<Boolean>,
        val getTrigger: Observable<Boolean>,
        val postTrigger: Observable<Boolean>
    )

    @SuppressLint("CheckResult")
    internal fun transform(input: Input): MutableLiveData<DashboardModel> {
        Observable.merge(input.didLoad, input.getTrigger)
            .subscribeIO()
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>().getGeneral()
                    .subscribeIO()
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            }

        input.postTrigger
            .subscribeIO()
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>().postGeneral()
                    .subscribeIO()
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            }

        return this.dashboardModel
    }

}

