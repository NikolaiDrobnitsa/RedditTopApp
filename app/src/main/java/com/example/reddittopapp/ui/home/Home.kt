package com.example.reddittopapp.ui.home

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.reddittopapp.domain.item.PostItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog

@Composable
fun PostCard(post: PostItem, onImageClick: () -> Unit) {
    val image = rememberImagePainter(data = post.url)

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
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
                    .clickable { onImageClick.invoke() } // Handle image click
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = post.author, fontWeight = FontWeight.Bold)
                Text(text = post.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val posts by homeViewModel.posts.collectAsState()

    val showFullScreenImage = remember { mutableStateOf(false) }
    val selectedImageUrl = remember { mutableStateOf("") }

    LazyColumn {
        items(posts) { post: PostItem ->
            PostCard(post = post) {
                // Handle image click
                selectedImageUrl.value = post.url ?: ""
                showFullScreenImage.value = true
            }
        }
    }

    if (showFullScreenImage.value) {
        FullScreenImageDialog(
            imageUrl = selectedImageUrl.value,
            onClose = {
                showFullScreenImage.value = false
            }
        )
    }
}

@Composable
fun FullScreenImageDialog(imageUrl: String, onClose: () -> Unit) {
    Dialog(
        onDismissRequest = { onClose.invoke() }
    ) {
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { downloadImage(context, imageUrl) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)

                ) {
                    Text(text = "Save" , fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { onClose.invoke() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),

                    ) {
                    Text(text = "Exit",color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


private fun downloadImage(context: Context, imageUrl: String) {
    val request = DownloadManager.Request(Uri.parse(imageUrl))
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    request.setDestinationInExternalPublicDir(
        Environment.DIRECTORY_PICTURES,
        "Image_${System.currentTimeMillis()}"
    )

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}