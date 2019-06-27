package id.lkand.kotlinplayground.activity.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import id.lkand.kotlinplayground.R
import id.lkand.kotlinplayground.databinding.ActivityDashboardBinding
import id.lkand.kotlinplayground.activity.dashboard.viewmodel.DashboardViewModel
import id.lkand.kotlinplayground.activity.main.MainActivity
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

internal class DashboardActivity : DaggerAppCompatActivity() {
    @Inject lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this, this.vmFactory).get(DashboardViewModel::class.java)
    }

    private val getTrigger = BehaviorSubject.create<Boolean>()
    private val postTrigger = BehaviorSubject.create<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView()
    }

    private fun bindView() {
        val didLoad = Observable.just(true)

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        this.viewModel.transform(
            DashboardViewModel.Input(
                didLoad,
                this.getTrigger,
                this.postTrigger
            )
        ).observe(this, Observer {
            this.binding.viewmodel = this.viewModel
            this.binding.handler = this
        })
    }

    fun handleTapGetButton() {
        this.getTrigger.onNext(true)
    }

    fun handleTapPostButton() {
        this.postTrigger.onNext(true)
    }

    fun handleNavigateButton() {
        Timer().schedule(500) {
            val intent = Intent(this@DashboardActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

}