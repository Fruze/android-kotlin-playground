package id.lkand.kotlinplayground.activity.dashboard.di

import dagger.Module
import dagger.Provides
import id.lkand.kotlinplayground.activity.dashboard.viewmodel.DashboardViewModel
import id.lkand.kotlinplayground.provider.SchedulerProvider
import id.lkand.kotlinplayground.provider.ViewModelFactoryProvider
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

@Module
internal class DashboardActivityModule {

    @Provides
    fun provideViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable
    ) =
            DashboardViewModel(schedulerProvider, compositeDisposable)

    @Provides
    fun provideViewModelFactory(dashboardViewModel: DashboardViewModel): ViewModelProvider.Factory {
        return ViewModelFactoryProvider(dashboardViewModel)
    }
}