package id.lkand.kotlinplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.lkand.kotlinplayground.activity.dashboard.di.DashboardActivityModule
import id.lkand.kotlinplayground.activity.dashboard.view.DashboardActivity

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        DashboardActivityModule::class
    ])
    abstract fun bindDashboardActivity(): DashboardActivity

}