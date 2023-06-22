package com.example.reddittopapp.data.remote

import com.example.reddittopapp.data.remote.model.PostModel
import com.example.reddittopapp.util.Constants.Companion.POSTS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {
    @GET("top.json")
    suspend fun getPosts(): Response<RedditResponse>
}

data class RedditResponse(
    val data: RedditData
)

data class RedditData(
    val children: List<RedditPost>
)

data class RedditPost(
    val data: PostModel
)