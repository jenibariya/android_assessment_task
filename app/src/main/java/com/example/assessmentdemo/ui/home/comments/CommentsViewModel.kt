package com.example.assessmentdemo.ui.home.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assessmentdemo.data.local.comments.CommentEntity
import com.example.assessmentdemo.data.repository.CommentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: CommentsRepository
) : ViewModel() {

    val commentText = MutableLiveData("")


    fun getCommentsByPost(postId: Int): LiveData<List<CommentEntity>> {
        return repository.getCommentsByPost(postId)
    }
}





