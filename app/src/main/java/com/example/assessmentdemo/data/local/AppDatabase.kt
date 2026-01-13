package com.example.assessmentdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessmentdemo.data.local.comments.CommentDao
import com.example.assessmentdemo.data.local.comments.CommentEntity
import com.example.assessmentdemo.data.local.posts.PostDao
import com.example.assessmentdemo.data.local.posts.PostEntity

@Database(
    entities = [
        PostEntity::class,
        CommentEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

}
