package com.samuel.posts.presentation.postdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.samuel.presentation.Resource
import br.com.samuel.presentation.ResourceState
import br.com.samuel.presentation.gone
import br.com.samuel.presentation.visible
import com.google.android.material.snackbar.Snackbar
import com.samuel.navigation.features.PostsNavigation
import com.samuel.posts.R
import com.samuel.posts.injectFeature
import com.samuel.posts.presentation.loadAvatar
import com.samuel.posts.presentation.model.CommentItem
import com.samuel.posts.presentation.model.PostItem
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.include_user_info.*
import kotlinx.android.synthetic.main.item_list_post.*
import org.koin.androidx.viewmodel.ext.viewModel

class PostDetailsActivity : AppCompatActivity() {

    private val vm: PostDetailsViewModel by viewModel()
    private val adapter = CommentsAdapter()
    private val userId by lazy { intent.getStringExtra(PostsNavigation.USER_ID_KEY) }
    private val postId by lazy { intent.getStringExtra(PostsNavigation.POST_ID_KEY) }
    private val snackbar by lazy {
        Snackbar.make(container, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { vm.getComments(postId, refresh = true) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        injectFeature()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        commentsRecyclerView.isNestedScrollingEnabled = false
        commentsRecyclerView.adapter = adapter

        if (savedInstanceState == null) {
            vm.getPost(UserIdPOstId(userId, postId))
            vm.getComments(postId, refresh = true)
        }

        vm.post.observe(this, Observer { updatePost(it) })
        vm.comments.observe(this, Observer { updateComments(it) })
    }

    private fun updatePost(postItem: PostItem) {
        postItem?.let {
            userAvatar.loadAvatar(it.email)
            userUsername.text = "@${it.username}"
            userName.text = it.name
            postTitle.text = it.title.capitalize()
            postBody.maxLines = Int.MAX_VALUE
            postBody.text = it.body.capitalize()
        }
    }

    private fun updateComments(resource: Resource<List<CommentItem>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> progressBar.visible()
                ResourceState.SUCCESS -> progressBar.gone()
                ResourceState.ERROR -> progressBar.gone()
            }
            it.data?.let { adapter.submitList(it) }
            it.message?.let { snackbar.show() }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}