package id.lkand.kotlinplayground.activity.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lkand.kotlinplayground.activity.dashboard.api.DashboardTarget
import id.lkand.kotlinplayground.activity.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import id.lkand.kotlinplayground.provider.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

internal class DashboardViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val dashboardModel = MutableLiveData<DashboardModel>()

    internal data class Input(
        val didLoad: Observable<Boolean>,
        val getTrigger: Observable<Boolean>,
        val postTrigger: Observable<Boolean>
    )

    internal fun transform(input: Input): MutableLiveData<DashboardModel> {
        this.compositeDisposable.add(Observable.merge(input.didLoad, input.getTrigger)
            .compose(this.schedulerProvider.getSchedulersForObservable())
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>().getGeneral()
                    .compose(this.schedulerProvider.getSchedulersForSingle())
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            })

        this.compositeDisposable.add(input.postTrigger
            .compose(this.schedulerProvider.getSchedulersForObservable())
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>().postGeneral()
                    .compose(this.schedulerProvider.getSchedulersForSingle())
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            })

        return this.dashboardModel
    }

    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.clear()
    }

}

