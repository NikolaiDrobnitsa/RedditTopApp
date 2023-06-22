package com.example.reddittopapp.data.remote

import com.example.reddittopapp.data.remote.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PostService @Inject constructor(private val postsApi: PostsApi) {
    suspend fun getPosts(): List<PostModel> {
        return withContext(Dispatchers.IO) {
            val response = postsApi.getPosts()
            val redditResponse = response.body()
            redditResponse?.data?.children?.map { it.data } ?: emptyList()
        }
    }
}