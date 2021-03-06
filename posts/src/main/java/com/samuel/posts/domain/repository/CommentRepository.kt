package com.samuel.posts.domain.repository

import com.samuel.posts.domain.model.Comment
import io.reactivex.Single

interface CommentRepository {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>>
}