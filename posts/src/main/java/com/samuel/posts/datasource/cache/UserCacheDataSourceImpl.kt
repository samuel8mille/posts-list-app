package com.samuel.posts.datasource.cache

import com.samuel.cache.ReactiveCache
import com.samuel.posts.data.datasource.UserCacheDataSource
import com.samuel.posts.domain.model.User
import io.reactivex.Single

class UserCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<User>>
) : UserCacheDataSource {

    val key = "User List"

    override fun get(): Single<List<User>> =
        cache.load(key)

    override fun set(list: List<User>): Single<List<User>> =
        cache.save(key, list)

    override fun set(item: User): Single<User> =
        cache.load(key)
            .map { it.filter { it.id != item.id }.plus(item) }
            .flatMap { set(it) }
            .map { item }

    override fun get(userId: String): Single<User> =
        cache.load(key)
            .map { it.first { it.id == userId } }

}