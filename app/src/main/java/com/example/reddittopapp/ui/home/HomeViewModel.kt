package com.example.reddittopapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reddittopapp.domain.GetPostsUseCase
import com.example.reddittopapp.domain.item.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _posts = MutableStateFlow(savedStateHandle.get<List<PostItem>>(STATE_POSTS_KEY) ?: emptyList())
    val posts: StateFlow<List<PostItem>> get() = _posts

    init {
        if (_posts.value.isEmpty()) {
            getPosts()
        }
    }

    public fun getPosts() {
        viewModelScope.launch {
            try {
                val posts = getPostsUseCase(100)
                _posts.value = posts
            } catch (e: Exception) {}
        }
    }

    fun saveState() {
        savedStateHandle.set(STATE_POSTS_KEY, _posts.value)
    }

    companion object {
        private const val STATE_POSTS_KEY = "state_posts_key"
    }
}