package com.samuel.posts.domain.repository

import com.samuel.posts.domain.model.User
import io.reactivex.Single

interface UserRepository {

    fun get(refresh: Boolean): Single<List<User>>

    fun get(userId: String, refresh: Boolean): Single<User>
}