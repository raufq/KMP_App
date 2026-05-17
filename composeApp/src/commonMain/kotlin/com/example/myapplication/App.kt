package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource

import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.decodeToImageBitmap

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
            LazyColumn {
                items(userList) { user->
                    ProfileContent(user,Alignment.Start)
                    ProfilePicture(user,16.dp)
                }
            }
        }
    }
}




@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: (UserProfile) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction(userProfile) },

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile, 70.dp)
            ProfileContent(userProfile, Alignment.Start)
        }
    }
}


@Composable
fun ProfilePicture(userProfile: UserProfile, profilePicSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (userProfile.status)
                Color.Green
            else Color.Red
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        /*Image(
            painter = rememberCoilPainter(userProfile.pictureUrl),
            contentDescription = "",
            modifier = Modifier.size(profilePicSize),
            contentScale = ContentScale.Crop
        )*/
        /*AsyncImage(
            model = userProfile.pictureUrl,
            contentDescription = "Translated description",
            modifier = Modifier.size(150.dp)
        )*/





        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = "Sample icon",
            modifier = Modifier.size(profilePicSize),
            colorFilter = ColorFilter.tint(Color.Blue),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(userProfile: UserProfile, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {

        Text(text = userProfile.name)


        Text(text = if (userProfile.status) "Active now" else "Offline")

    }
}