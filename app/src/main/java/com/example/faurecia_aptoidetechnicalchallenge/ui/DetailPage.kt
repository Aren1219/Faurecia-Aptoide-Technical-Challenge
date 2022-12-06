package com.example.faurecia_aptoidetechnicalchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.faurecia_aptoidetechnicalchallenge.model.App
import com.example.faurecia_aptoidetechnicalchallenge.ui.destinations.MainPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Composable
@Destination
fun DetailPage(app: App, navigator: DestinationsNavigator) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .fillMaxSize()
    ) {
        Box() {
            GlideImage(app.graphic)
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .size(48.dp),
                onClick = { navigator.navigate(MainPageDestination) },
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack, "navigate up",
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(150.dp)
        ) {
            GlideImage(
                app.icon,
                Modifier
                    .clip(RoundedCornerShape(15))
                    .size(150.dp)
            )
            Column(
                Modifier
                    .padding(horizontal = 18.dp)
                    .fillMaxSize(),
                Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = app.name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(text = app.storeName)
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "DOWNLOAD")
                }
            }
        }
        Divider(color = Color.Gray)
    }

}