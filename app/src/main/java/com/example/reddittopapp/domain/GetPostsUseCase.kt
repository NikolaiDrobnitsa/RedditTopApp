package com.example.reddittopapp.domain

import com.example.reddittopapp.domain.item.PostItem
import com.example.reddittopapp.repo.PostRepository
import javax.inject.Inject


class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(limit: Int): List<PostItem> {
        val posts = postRepository.getPosts(limit)
        return posts.filter { it.url != null && it.url.isNotEmpty() }
    }
}