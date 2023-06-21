package com.example.reddittopapp.data.remote.model

data class PostModel(
    val id: String,
    val author: String,
    val created: Double,
    val thumbnail: String,
    val title: String,
    val num_comments: Int,

    val thumbnail_height: Int,
    val thumbnail_width: Int,

)