package id.lkand.kotlinplayground.feature.dashboard.viewmodel

import id.lkand.kotlinplayground.feature.dashboard.repository.DashboardRepository

class DashboardViewModel {

    // TODO: Networking
    init {
        val repo = DashboardRepository()
        repo.getGeneral()
    }

    // TODO: Modeling

}