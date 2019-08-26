package com.samuel.posts.presentation.postdetails

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.samuel.presentation.Resource
import br.com.samuel.presentation.setError
import br.com.samuel.presentation.setLoading
import br.com.samuel.presentation.setSuccess
import com.samuel.posts.domain.usecase.CommentsUseCase
import com.samuel.posts.domain.usecase.UserPostUseCase
import com.samuel.posts.presentation.model.CommentItem
import com.samuel.posts.presentation.model.PostItem
import com.samuel.posts.presentation.model.mapToPresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class UserIdPOstId(val userId: String, val postId: String)

class PostDetailsViewModel constructor(
    private val userPostUseCase: UserPostUseCase,
    private val commentsUseCase: CommentsUseCase
) : ViewModel() {

    val post = MutableLiveData<PostItem>()
    val comments = MutableLiveData<Resource<List<CommentItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPost(ids: UserIdPOstId) =
        compositeDisposable.add(userPostUseCase.get(ids.userId, ids.postId, false)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ post.postValue(it) }, { it.message })
        )

    fun getComments(postId: String, refresh: Boolean = false) =
        compositeDisposable.add(commentsUseCase.get(postId, refresh)
            .doOnSubscribe { comments.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ comments.setSuccess(it) }, { comments.setError(it.message) })
        )
}