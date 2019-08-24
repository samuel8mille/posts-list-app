package samuel.com.br.testedaggerapp.base

import android.arch.lifecycle.ViewModel
import samuel.com.br.testedaggerapp.injection.component.DaggerViewModelInjector
import samuel.com.br.testedaggerapp.injection.component.ViewModelInjector
import samuel.com.br.testedaggerapp.injection.module.NetworkModule
import samuel.com.br.testedaggerapp.ui.post.PostListViewModel

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