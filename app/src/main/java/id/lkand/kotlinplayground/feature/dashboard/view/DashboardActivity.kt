package id.lkand.kotlinplayground.feature.dashboard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.lkand.kotlinplayground.R
import id.lkand.kotlinplayground.feature.dashboard.viewmodel.DashboardViewModel

class DashboardActivity : AppCompatActivity() {
    private val viewModel = DashboardViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_dashboard)
        this.bindView()
    }

    private fun bindView() {
        this.viewModel.transform()
    }

    // TODO: DataBinding
    // TODO: Coroutine, Rx
}