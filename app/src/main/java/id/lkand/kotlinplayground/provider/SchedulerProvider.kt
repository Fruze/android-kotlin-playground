package id.lkand.kotlinplayground.provider

import io.reactivex.*

internal class SchedulerProvider(private val backgroundScheduler: Scheduler, private val foregroundScheduler: Scheduler) {

    fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
        return { observable: Observable<T> ->
            observable.subscribeOn(this.backgroundScheduler)
                .observeOn(this.foregroundScheduler)
        }
    }

    fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {
        return { single: Single<T> ->
            single.subscribeOn(this.backgroundScheduler)
                .observeOn(this.foregroundScheduler)
        }
    }

}