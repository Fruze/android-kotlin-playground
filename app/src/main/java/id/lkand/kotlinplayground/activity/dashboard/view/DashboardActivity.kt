package id.lkand.kotlinplayground.activity.dashboard.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import id.lkand.kotlinplayground.R
import id.lkand.kotlinplayground.databinding.ActivityDashboardBinding
import id.lkand.kotlinplayground.activity.dashboard.viewmodel.DashboardViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

internal class DashboardActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    @Inject lateinit var viewModel: DashboardViewModel

    private val getTrigger = BehaviorSubject.create<Boolean>()
    private val postTrigger = BehaviorSubject.create<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        this.bindView()
    }

    private fun bindView() {
        val didLoad = Observable.just(true)

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

}