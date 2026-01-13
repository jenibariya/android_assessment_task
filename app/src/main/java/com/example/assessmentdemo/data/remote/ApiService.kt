package com.example.assessmentdemo.data.remote

import com.example.assessmentdemo.data.model.CommentDto
import com.example.assessmentdemo.data.model.PostDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<PostDto>


    @GET("comments")
    suspend fun getAllComments(): List<CommentDto>


}