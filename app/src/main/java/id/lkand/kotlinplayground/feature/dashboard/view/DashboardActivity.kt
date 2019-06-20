package id.lkand.kotlinplayground.feature.dashboard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.lkand.kotlinplayground.R
import id.lkand.kotlinplayground.feature.dashboard.viewmodel.DashboardViewModel

class DashboardActivity : AppCompatActivity() {
    val viewModel = DashboardViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    // TODO: ViewModel
    fun bindView() {

    }

    // TODO: DataBinding
    // TODO: Coroutine, Rx
}