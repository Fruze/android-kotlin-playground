package id.lkand.kotlinplayground.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.lkand.kotlinplayground.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        this.setBottomBar()
    }

    private fun setBottomBar() {
        with(findViewById<BottomNavigationView>(R.id.main_bottom_navigation)) {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_first -> {
                        Log.d("Timber", "First")
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.action_second -> {
                        Log.d("Timber", "Second")
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.action_third -> {
                        Log.d("Timber", "Third")
                        return@setOnNavigationItemSelectedListener true
                    }
                }

                false
            }
        }

    }
}
