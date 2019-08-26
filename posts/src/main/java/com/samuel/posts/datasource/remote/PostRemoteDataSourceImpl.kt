package com.samuel.posts.datasource.remote

import com.samuel.posts.data.datasource.PostRemoteDataSource
import com.samuel.posts.datasource.model.mapToDomain
import com.samuel.posts.domain.model.Post
import io.reactivex.Single

class PostRemoteDataSourceImpl constructor(
    private val api: PostsApi
) : PostRemoteDataSource {

    override fun get(): Single<List<Post>> =
        api.getPosts()
            .map { it.mapToDomain() }

    override fun get(postId: String): Single<Post> =
        api.getPost(postId)
            .map { it.mapToDomain() }
}