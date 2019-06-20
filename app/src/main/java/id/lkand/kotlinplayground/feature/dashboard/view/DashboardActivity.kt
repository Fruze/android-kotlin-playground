package id.lkand.kotlinplayground.feature.dashboard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.lkand.kotlinplayground.R
import id.lkand.kotlinplayground.feature.dashboard.repository.Controller

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

//        val asd = Controller()
//        asd.start()
    }

    // TODO: ViewModel
    // TODO: DataBinding
    // TODO: Coroutine, Rx
}