package com.samuel.posts.presentation

import android.widget.ImageView
import br.com.samuel.presentation.loadImageRound

fun ImageView.loadAvatar(email: String) =
    loadImageRound("https://api.adorable.io/avatars/285/$email")