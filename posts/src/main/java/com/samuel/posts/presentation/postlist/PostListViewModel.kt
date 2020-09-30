package com.samuel.posts.presentation.postlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.samuel.presentation.Resource
import br.com.samuel.presentation.setError
import br.com.samuel.presentation.setLoading
import br.com.samuel.presentation.setSuccess
import com.samuel.posts.domain.usecase.UsersPostsUseCase
import com.samuel.posts.presentation.model.PostItem
import com.samuel.posts.presentation.model.mapToPresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostListViewModel constructor(
    private val usersPostsUseCase: UsersPostsUseCase
) : ViewModel() {

    val posts = MutableLiveData<Resource<List<PostItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun get(refresh: Boolean = false) =
        compositeDisposable.add(usersPostsUseCase.get(refresh)
            .doOnSubscribe { posts.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe(
                { posts.setSuccess(it) },
                { posts.setError(it.message) }
            )

        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}