package com.example.assessmentdemo.data.model

import com.google.gson.annotations.SerializedName

data class CommentDto(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)


