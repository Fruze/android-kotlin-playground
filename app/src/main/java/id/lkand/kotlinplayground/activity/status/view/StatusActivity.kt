package id.lkand.kotlinplayground.activity.status.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.DaggerFragment
import id.lkand.kotlinplayground.R

internal class StatusActivity : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.activity_status, container, false)
    }

}