package samuel.com.br.postlistapp.ui.post

import android.arch.lifecycle.MutableLiveData
import samuel.com.br.postlistapp.base.BaseViewModel
import samuel.com.br.postlistapp.model.Post

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