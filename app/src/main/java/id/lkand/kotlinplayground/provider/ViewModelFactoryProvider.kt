package id.lkand.kotlinplayground.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class ViewModelFactoryProvider<V: ViewModel>(private val viewModel: V) : ViewModelProvider.Factory {

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}