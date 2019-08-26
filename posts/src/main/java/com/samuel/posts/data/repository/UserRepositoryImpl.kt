package com.samuel.posts.data.repository

import com.samuel.posts.data.datasource.UserCacheDataSource
import com.samuel.posts.data.datasource.UserRemoteDataSource
import com.samuel.posts.domain.model.User
import com.samuel.posts.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl constructor(
    private val cacheDataSource: UserCacheDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun get(refresh: Boolean): Single<List<User>> =
        when (refresh) {
            true -> remoteDataSource.get()
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get()
                .onErrorResumeNext(get(true))
        }


    override fun get(userId: String, refresh: Boolean): Single<User> =
        when (refresh) {
            true -> remoteDataSource.get(userId)
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get(userId)
                .onErrorResumeNext { get(userId, true) }
        }
}