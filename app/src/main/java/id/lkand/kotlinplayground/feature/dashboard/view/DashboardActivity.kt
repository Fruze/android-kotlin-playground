package id.lkand.kotlinplayground.feature.dashboard.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.lkand.kotlinplayground.R
import id.lkand.kotlinplayground.feature.dashboard.viewmodel.DashboardViewModel

class DashboardActivity : AppCompatActivity() {
    private val viewModel: DashboardViewModel by lazy {
        ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_dashboard)
        this.bindView()
    }

    private fun bindView() {
        this.viewModel.transform()

        this.viewModel.dashboardModel.observe(this, Observer { model ->
            Log.d("DebugUtil", model.toString())
        })
    }

    // TODO: DataBinding
    // TODO: Coroutine, Rx
}