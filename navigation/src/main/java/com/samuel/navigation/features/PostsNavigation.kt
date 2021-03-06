package com.samuel.navigation.features

import android.content.Intent
import com.samuel.navigation.loadIntentOrNull

object PostsNavigation : DynamicFeature<Intent> {

    const val USER_ID_KEY = "USER_ID_KEY"
    const val POST_ID_KEY = "POST_ID_KEY"

    private const val POST_LIST =
        "com.samuel.posts.presentation.postlist.PostListActivity"
    private const val POST_DETAILS =
        "com.samuel.posts.presentation.postdetails.PostDetailsActivity"

    override val dynamicStart: Intent?
        get() = POST_LIST.loadIntentOrNull()

    fun postDetails(userId: String, postId: String): Intent? =
        POST_DETAILS.loadIntentOrNull()
            ?.apply {
                putExtra(USER_ID_KEY, userId)
                putExtra(POST_ID_KEY, postId)
            }
}
