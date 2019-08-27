package com.samuel.posts.domain.usecase

import com.samuel.posts.domain.model.Comment
import com.samuel.posts.domain.repository.CommentRepository
import io.reactivex.Single

class CommentsUseCase constructor(
    private val commentRepository: CommentRepository
) {
    fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
    commentRepository.get(postId, refresh)
}