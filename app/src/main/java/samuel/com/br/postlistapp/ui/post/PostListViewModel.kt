package samuel.com.br.postlistapp.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import samuel.com.br.testedaggerapp.R
import samuel.com.br.postlistapp.base.BaseViewModel
import samuel.com.br.postlistapp.model.Post
import samuel.com.br.postlistapp.network.PostApi
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val postListAdapter: PostListAdapter =
        PostListAdapter()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap { dbPostList ->
                if (dbPostList.isEmpty())
                    postApi.getPost().concatMap { apiPostList ->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { excpection -> onRetrievePostListError(excpection) }
            )
    }


    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(exception: Throwable) {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}