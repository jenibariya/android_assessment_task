package com.example.assessmentdemo.ui.home.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentdemo.data.local.posts.PostEntity
import com.example.assessmentdemo.data.repository.CommentRepository
import com.example.assessmentdemo.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository,
    private val commentsRepository: CommentRepository
) : ViewModel() {

    val posts: LiveData<List<PostEntity>> = repository.getPosts()
    val favorites: LiveData<List<PostEntity>> = repository.getFavorites()

    init {
        loadPosts()
        commentsRepository.syncAllComments()

    }

    fun loadPosts() {
        viewModelScope.launch {
            repository.fetchPostsFromApi()
        }
    }

    fun toggleFavorite(post: PostEntity) {
        viewModelScope.launch {
            repository.toggleFavorite(post)
        }
    }
}
