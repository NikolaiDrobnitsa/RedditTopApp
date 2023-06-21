package com.example.reddittopapp.domain.item

import com.example.reddittopapp.data.remote.model.PostModel

data class PostItem(

    val id: String,
    val author: String,
    val created: Double,
    val thumbnail: String,
    val title: String,
    val num_comments: Int
)

fun PostModel.toPostItem() = PostItem(id, author, created, thumbnail, title, num_comments)
