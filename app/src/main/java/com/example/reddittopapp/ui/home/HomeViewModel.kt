package com.example.reddittopapp.ui.home

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
class HomeViewModel @Inject constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {
    private  val _posts = MutableStateFlow(emptyList<PostItem>())
    val posts: StateFlow<List<PostItem>> get() = _posts

    init {
        getPosts()
    }

    private fun getPosts() {

        viewModelScope.launch {
            try{
                val posts = getPostsUseCase()
                _posts.value = posts
            }catch (_: Exception){}
        }

    }
}