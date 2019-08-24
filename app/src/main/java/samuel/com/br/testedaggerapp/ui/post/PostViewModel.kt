package samuel.com.br.testedaggerapp.ui.post

import android.arch.lifecycle.MutableLiveData
import samuel.com.br.testedaggerapp.base.BaseViewModel
import samuel.com.br.testedaggerapp.model.Post

class PostViewModel: BaseViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle():MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody():MutableLiveData<String> {
        return postBody
    }
}