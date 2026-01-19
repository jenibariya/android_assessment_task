package com.example.assessmentdemo.data.repository

import androidx.lifecycle.LiveData
import com.example.assessmentdemo.data.local.comments.CommentDao
import com.example.assessmentdemo.data.local.comments.CommentEntity
import com.example.assessmentdemo.data.remote.ApiService
import com.example.assessmentdemo.utils.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsRepository @Inject constructor(
    private val api: ApiService,
    private val dao: CommentDao,
    private val networkHelper: NetworkHelper
) {

    fun syncAllComments() {
        if (networkHelper.isNetworkAvailable()) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = api.getAllComments()
                val entities = response.map {
                    CommentEntity(
                        id = it.id,
                        postId = it.postId,
                        name = it.name,
                        email = it.email,
                        body = it.body
                    )
                }
                dao.insertComments(entities)
            }
        }
    }

    fun getCommentsByPost(postId: Int): LiveData<List<CommentEntity>> {
        return dao.getCommentsByPostId(postId)
    }
}






