package com.example.assessmentdemo.data.local.posts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.assessmentdemo.data.model.FavoriteDto

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE isFavorite = 1")
    fun getFavoritePosts(): LiveData<List<PostEntity>>


    @Query("SELECT id, isFavorite FROM posts")
    suspend fun getFavoriteMap(): List<FavoriteDto>

    @Update
    suspend fun updatePost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)
}
