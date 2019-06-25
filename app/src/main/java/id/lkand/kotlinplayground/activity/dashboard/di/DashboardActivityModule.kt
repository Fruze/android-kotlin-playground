package id.lkand.kotlinplayground.activity.dashboard.di

import dagger.Module
import dagger.Provides
import id.lkand.kotlinplayground.activity.dashboard.viewmodel.DashboardViewModel
import id.lkand.kotlinplayground.provider.SchedulerProvider

@Module
internal class DashboardActivityModule {

    @Provides
    fun provideViewModel(schedulerProvider: SchedulerProvider) = DashboardViewModel(schedulerProvider)

}