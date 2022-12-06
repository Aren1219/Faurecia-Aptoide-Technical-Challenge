package com.example.faurecia_aptoidetechnicalchallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
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
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary.copy(0.6f))
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
                    .padding(start = 18.dp)
                    .fillMaxSize(),
                Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = app.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = app.storeName)
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "INSTALL")
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.semantics(mergeDescendants = true) { }
            ) {
                AppRating(rating = app.rating, color = Color.Black)
                Text(text = "App rating", modifier = Modifier.clearAndSetSemantics { })
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.semantics(mergeDescendants = true) { }
            ) {
                Text(text = "${app.downloads}")
                Text(text = "downloads")
            }

        }

        Divider(color = Color.Gray)

        Column(
            modifier = Modifier
                .padding(12.dp)
                .semantics(mergeDescendants = true) { }
        ) {
            Text(text = "date added: ${app.added.formatDate()}")
            Text(text = "last updated: ${app.updated.formatDate()}")
        }

        Text(text = "App description: ...", modifier = Modifier.padding(horizontal = 12.dp))
    }
}

fun String.formatDate() = this.take(10)