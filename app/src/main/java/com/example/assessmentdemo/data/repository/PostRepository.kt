package com.example.assessmentdemo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.assessmentdemo.data.local.posts.PostDao
import com.example.assessmentdemo.data.local.posts.PostEntity
import com.example.assessmentdemo.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {

    fun getPosts(): LiveData<List<PostEntity>> =
        postDao.getAllPosts()

    fun getFavorites(): LiveData<List<PostEntity>> =
        postDao.getFavoritePosts()

    suspend fun fetchPostsFromApi() {
        try {
            val postsFromApi = apiService.getPosts()

            // 🔹 Preserve favorites from Room
            val favoriteMap = postDao.getFavoriteMap()
                .associate { it.id to it.isFavorite }

            val entities = postsFromApi.map {
                PostEntity(
                    id = it.id,
                    userId = it.userId,
                    title = it.title,
                    body = it.body,
                    isFavorite = favoriteMap[it.id] ?: false
                )
            }

            postDao.insertPosts(entities)

        } catch (e: Exception) {
            // ✅ OFFLINE MODE
            // Do NOTHING → Room already has posts & favorites
            Log.e("PostRepo", "Network unavailable, using cached posts")
        }
    }


    suspend fun toggleFavorite(post: PostEntity) {
        val updatedPost = post.copy(isFavorite = !post.isFavorite)
        postDao.updatePost(updatedPost)
    }
}
