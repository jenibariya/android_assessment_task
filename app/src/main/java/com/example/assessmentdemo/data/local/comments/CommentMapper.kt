package com.example.assessmentdemo.data.local.comments

import com.example.assessmentdemo.data.model.CommentDto

fun CommentDto.toEntity(): CommentEntity {
    return CommentEntity(
        id = id,
        postId = postId,
        name = name,
        email = email,
        body = body
    )
}
