package id.lkand.kotlinplayground.activity.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lkand.kotlinplayground.extension.subscribeIO
import id.lkand.kotlinplayground.activity.dashboard.api.DashboardTarget
import id.lkand.kotlinplayground.activity.dashboard.model.DashboardModel
import id.lkand.kotlinplayground.provider.NetworkProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

internal class DashboardViewModel : ViewModel() {
    private val compositeDisposable by lazy { CompositeDisposable() }
    val dashboardModel = MutableLiveData<DashboardModel>()

    internal data class Input(
        val didLoad: Observable<Boolean>,
        val getTrigger: Observable<Boolean>,
        val postTrigger: Observable<Boolean>
    )

    internal fun transform(input: Input): MutableLiveData<DashboardModel> {
        this.compositeDisposable.add(Observable.merge(input.didLoad, input.getTrigger)
            .subscribeIO()
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>().getGeneral()
                    .subscribeIO()
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            })

        this.compositeDisposable.add(input.postTrigger
            .subscribeIO()
            .subscribeBy {
                NetworkProvider.request<DashboardTarget>().postGeneral()
                    .subscribeIO()
                    .subscribeBy { response: DashboardModel ->
                        this@DashboardViewModel.dashboardModel.value = response
                    }
            })

        return this.dashboardModel
    }

    override fun onCleared() {
        super.onCleared()

        this.compositeDisposable.clear()
        this.compositeDisposable.dispose()
    }

}

