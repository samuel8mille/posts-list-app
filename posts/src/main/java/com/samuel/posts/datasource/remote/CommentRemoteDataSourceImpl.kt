package com.samuel.posts.datasource.remote

import com.samuel.posts.data.datasource.CommentRemoteDataSource
import com.samuel.posts.datasource.model.mapToDomain
import com.samuel.posts.domain.model.Comment
import io.reactivex.Single

class CommentRemoteDataSourceImpl constructor(
    private val api: CommentsApi
) : CommentRemoteDataSource {

    override fun get(postId: String): Single<List<Comment>> =
        api.getComments(postId)
            .map { it.mapToDomain() }
}