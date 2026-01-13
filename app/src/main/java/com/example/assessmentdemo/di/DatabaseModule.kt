package com.example.assessmentdemo.di

import android.content.Context
import androidx.room.Room
import com.example.assessmentdemo.data.local.AppDatabase
import com.example.assessmentdemo.data.local.posts.PostDao
import com.example.assessmentdemo.data.local.comments.CommentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "assessment_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePostDao(db: AppDatabase): PostDao {
        return db.postDao()
    }

    @Provides
    fun provideCommentDao(db: AppDatabase): CommentDao {
        return db.commentDao()
    }
}