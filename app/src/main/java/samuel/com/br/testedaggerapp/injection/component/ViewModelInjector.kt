package samuel.com.br.testedaggerapp.injection.component

import dagger.Component
import samuel.com.br.testedaggerapp.injection.module.NetworkModule
import samuel.com.br.testedaggerapp.ui.post.PostListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}