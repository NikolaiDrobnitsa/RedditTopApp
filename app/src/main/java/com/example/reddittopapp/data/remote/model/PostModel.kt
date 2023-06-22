package com.example.reddittopapp.data.remote.model

data class PostModel(
    val id: String,
    val author: String,
    val url: String?,
    val title: String,
    val num_comments: Int,
    val created: Long
)