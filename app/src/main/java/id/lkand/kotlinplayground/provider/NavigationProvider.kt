package id.lkand.kotlinplayground.provider

import android.content.Context
import android.content.Intent

internal class NavigationProvider {

    inline fun <reified T> navigate(C : Context, R : Class<T>) {
        val intent = Intent(C, R)
        C.startActivity(intent)
    }

}