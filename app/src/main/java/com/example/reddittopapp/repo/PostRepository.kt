package com.example.reddittopapp.repo

import com.example.reddittopapp.data.remote.PostService
import com.example.reddittopapp.domain.item.PostItem
import com.example.reddittopapp.domain.item.toPostItem
import javax.inject.Inject


class PostRepository @Inject constructor(private val postService: PostService) {
    suspend fun getPosts(limit: Int): List<PostItem> {
        return postService.getPosts(limit).map {
            it.toPostItem()
        }
    }
}