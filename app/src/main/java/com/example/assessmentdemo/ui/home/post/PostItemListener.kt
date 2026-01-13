package com.example.assessmentdemo.ui.home.post

import com.example.assessmentdemo.data.local.posts.PostEntity

interface PostItemListener {
    fun onFavoriteClicked(post: PostEntity)
    fun onCommentClicked(post: PostEntity)
}
