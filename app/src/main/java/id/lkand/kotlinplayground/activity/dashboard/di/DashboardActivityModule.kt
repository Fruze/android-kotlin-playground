package id.lkand.kotlinplayground.activity.dashboard.di

import dagger.Module
import dagger.Provides
import id.lkand.kotlinplayground.activity.dashboard.viewmodel.DashboardViewModel
import id.lkand.kotlinplayground.provider.SchedulerProvider
import id.lkand.kotlinplayground.di.ViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider

@Module
internal class DashboardActivityModule {

    @Provides
    fun provideViewModel(schedulerProvider: SchedulerProvider) = DashboardViewModel(schedulerProvider)

    @Provides
    fun provideViewModelFactory(dashboardViewModel: DashboardViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(dashboardViewModel)
    }
}