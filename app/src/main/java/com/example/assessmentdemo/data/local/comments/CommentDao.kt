package com.example.assessmentdemo.data.local.comments

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    fun getAllComments(): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getCommentsByPostId(postId: Int): LiveData<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)
}



