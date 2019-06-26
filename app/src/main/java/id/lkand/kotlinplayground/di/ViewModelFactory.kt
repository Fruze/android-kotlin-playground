package id.lkand.kotlinplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class ViewModelProviderFactory<V: ViewModel>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (viewModel is T) {
            return viewModel as T
//        }
//        throw IllegalArgumentException("Unknown class name")
    }

}