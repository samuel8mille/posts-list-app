package com.samuel.posts.datasource.remote

import com.samuel.posts.data.datasource.UserRemoteDataSource
import com.samuel.posts.datasource.model.mapToDomain
import com.samuel.posts.domain.model.User
import io.reactivex.Single

class UserRemoteDataSourceImpl constructor(
    private val api: UsersApi
) : UserRemoteDataSource {

    override fun get(): Single<List<User>> =
        api.getUsers()
            .map { it.mapToDomain() }

    override fun get(userId: String): Single<User> =
        api.getUser(userId)
            .map { it.mapToDomain() }
}