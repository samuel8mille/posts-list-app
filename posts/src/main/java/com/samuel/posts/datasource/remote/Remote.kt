package com.samuel.posts.datasource.remote

import com.samuel.posts.datasource.model.CommentEntity
import com.samuel.posts.datasource.model.PostEntity
import com.samuel.posts.datasource.model.UserEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {

    @GET("posts/")
    fun getPosts(): Single<List<PostEntity>>

    @GET("posts/{id}")
    fun getPost(@Path("id") postId: String): Single<PostEntity>

}

interface UsersApi {

    @GET("users/")
    fun getUsers(): Single<List<UserEntity>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<UserEntity>

}

interface CommentsApi {

    @GET("comments/")
    fun getComments(@Query("postId") postId: String): Single<List<CommentEntity>>
}