package com.example.reddittopapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.reddittopapp.domain.item.PostItem
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val posts by homeViewModel.posts.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getPosts()
    }

    LazyColumn {
        items(posts) { post ->
            PostCard(post = post)
        }
    }
}

@Composable
fun PostCard(post: PostItem){


    val image = rememberImagePainter(data = post.url)

    Card(

        elevation = 5.dp,
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .fillMaxSize()

    ) {

        Column {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)

            )

            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = post.author, fontWeight = FontWeight.Bold)
                Text(text = post.title, maxLines = 1 , overflow = TextOverflow.Ellipsis)




            }

            
        }

    }
}