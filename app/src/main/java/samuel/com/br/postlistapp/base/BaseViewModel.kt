package samuel.com.br.postlistapp.base

import android.arch.lifecycle.ViewModel
import samuel.com.br.postlistapp.injection.component.DaggerViewModelInjector
import samuel.com.br.postlistapp.injection.component.ViewModelInjector
import samuel.com.br.postlistapp.injection.module.NetworkModule
import samuel.com.br.postlistapp.ui.post.PostListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}