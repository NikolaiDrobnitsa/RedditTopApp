package com.example.reddittopapp.data.remote

import com.example.reddittopapp.data.remote.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PostService @Inject constructor(private val postsApi: PostsApi) {
    suspend fun getPosts(limit: Int): List<PostModel> {
        return withContext(Dispatchers.IO) {
            val response = postsApi.getPosts(limit)
            val redditResponse = response.body()
            redditResponse?.data?.children?.map { it.data }?.filter { it.url != null && isImageUrl(it.url) }
                ?: emptyList()
        }
    }

    private fun isImageUrl(url: String): Boolean {
        return url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".gif")
    }
}