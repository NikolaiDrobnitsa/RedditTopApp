package com.example.reddittopapp.domain.item

import com.example.reddittopapp.data.remote.model.PostModel

data class PostItem(
    val id: String,
    val author: String,
    val url: String?,
    val title: String,
    val num_comments: Int,
    val created: Long
)

fun PostModel.toPostItem() = PostItem(id, author, url, title, num_comments, created)
