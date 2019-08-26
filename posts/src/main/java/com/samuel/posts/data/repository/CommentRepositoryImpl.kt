package com.samuel.posts.data.repository

import com.samuel.posts.data.datasource.CommentCacheDataSource
import com.samuel.posts.data.datasource.CommentRemoteDataSource
import com.samuel.posts.domain.model.Comment
import com.samuel.posts.domain.repository.CommentRepository
import io.reactivex.Single

class CommentRepositoryImpl constructor(
    private val cacheDataSource: CommentCacheDataSource,
    private val remoteDataSource: CommentRemoteDataSource
) : CommentRepository {

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(postId, it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }

}