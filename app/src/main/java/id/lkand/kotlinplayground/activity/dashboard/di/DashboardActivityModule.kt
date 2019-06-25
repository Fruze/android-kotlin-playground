package id.lkand.kotlinplayground.activity.dashboard.di

import dagger.Module
import dagger.Provides
import id.lkand.kotlinplayground.activity.dashboard.viewmodel.DashboardViewModel

@Module
internal class DashboardActivityModule {

    @Provides
    fun provideViewModel() = DashboardViewModel()

}