package com.example.reddittopapp.domain

import com.example.reddittopapp.domain.item.PostItem
import com.example.reddittopapp.repo.PostRepository
import javax.inject.Inject


class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(): List<PostItem> {
        return postRepository.getPosts()
    }
}