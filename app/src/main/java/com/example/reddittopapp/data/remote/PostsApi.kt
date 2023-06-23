package com.example.reddittopapp.data.remote

import com.example.reddittopapp.data.remote.model.PostModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("top.json")
    suspend fun getPosts(@Query("limit") limit: Int): Response<RedditResponse>
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