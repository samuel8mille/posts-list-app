package com.samuel.posts.domain.repository

import com.samuel.posts.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}