package com.samuel.posts.domain.usecase

import com.samuel.posts.domain.model.Post
import com.samuel.posts.domain.model.User
import com.samuel.posts.domain.repository.PostRepository
import com.samuel.posts.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
 
data class CombinedUserPost(val user: User, val post: Post)

class UsersPostsUseCase constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    fun get(refresh: Boolean): Single<List<CombinedUserPost>> =
        Single.zip(userRepository.get(refresh), postRepository.get(refresh),
            BiFunction { userList, postList -> map(userList, postList) })
}

class UserPostUseCase constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    fun get(userId: String, postId: String, refresh: Boolean): Single<CombinedUserPost> =
        Single.zip(userRepository.get(userId, refresh), postRepository.get(postId, refresh),
            BiFunction { user, post -> map(user, post) })
}

fun map(user: User, post: Post): CombinedUserPost = CombinedUserPost(user, post)

fun map(userList: List<User>, postList: List<Post>): List<CombinedUserPost> =
    postList.map { post -> CombinedUserPost(userList.first { post.userId == it.id }, post) }
