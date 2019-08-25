package com.samuel.posts.presentation.postdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuel.posts.R
import org.koin.androidx.viewmodel.ext.viewModel

class PostDetailsActivity : AppCompatActivity() {

    private val vm: PostDetailsViewModel by viewModel()
    private val adapter = CommentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
    }
}