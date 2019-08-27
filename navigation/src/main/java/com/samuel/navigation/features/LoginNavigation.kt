package com.samuel.navigation.features

import android.content.Intent
import com.samuel.navigation.loadIntentOrNull

object LoginNavigation : DynamicFeature<Intent> {

    private const val LOGIN =
        "com.samuel.login.presentation.login.LoginActivity"

    override val dynamicStart: Intent?
        get() = LOGIN.loadIntentOrNull()
}