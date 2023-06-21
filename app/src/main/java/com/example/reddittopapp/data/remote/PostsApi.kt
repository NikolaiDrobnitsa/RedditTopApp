package com.example.reddittopapp.data.remote

import com.example.reddittopapp.data.remote.model.PostModel
import com.example.reddittopapp.util.Constants.Companion.POSTS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET(POSTS_ENDPOINT)
    suspend fun getPosts(): Response<List<PostModel>>
}