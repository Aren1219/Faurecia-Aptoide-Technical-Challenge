package com.example.faurecia_aptoidetechnicalchallenge.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.faurecia_aptoidetechnicalchallenge.R
import com.example.faurecia_aptoidetechnicalchallenge.model.App
import com.example.faurecia_aptoidetechnicalchallenge.ui.destinations.DetailPageDestination
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination(start = true)
@Composable
fun MainPage(
    viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.appList.collectAsState()
    val appList = uiState.data?.responses?.listApps?.datasets?.all?.data?.list
    when (uiState) {
        is Resource.Success -> {
            appList?.let {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    SectionTitle(title = "Editors choice") {}

                    EditorsChoiceLazyRow(
                        list = appList,
                        viewModel.editorsChoiceListState
                    ) { item -> navigator.navigate(DetailPageDestination(item)) }

                    SectionTitle(title = "Local top apps") {}

                    LocalTopAppsLazyRow(
                        list = appList.asReversed(),
                        viewModel.localTopAppsListState
                    ) { item -> navigator.navigate(DetailPageDestination(item)) }
                }
            }
        }
        is Resource.Loading -> {
            ShowLoadingIndicator()
        }
        is Resource.Error -> {
            uiState.message?.let { ShowErrorMessage(message = it) }
        }
    }
}

@Composable
fun SectionTitle(title: String, onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp, 28.dp, 12.dp, 12.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .semantics { heading() },
            style = MaterialTheme.typography.titleLarge
        )
        ClickableText(
            text = AnnotatedString(
                "MORE", SpanStyle(color = MaterialTheme.colorScheme.primary)
            ),
            onClick = { onClick() },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClickLabel = "show more $title") {},
        )
    }
}

@Composable
fun EditorsChoiceLazyRow(list: List<App>, listState: LazyListState, onItemClick: (App) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp, 0.dp),
        state = listState,
        modifier = Modifier.semantics { contentDescription = "Editors Choice" }
    ) {
        items(list) { item ->
            EditorsChoiceRowItem(app = item) { onItemClick(item) }
        }
    }
}

@Composable
fun EditorsChoiceRowItem(app: App, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .semantics(mergeDescendants = true) { }
            .clickable(onClickLabel = stringResource(id = R.string.action_open_app_detail)) {
                onClick()
            }
            .size(350.dp, 200.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.fillMaxSize()) {
            GlideImage(
                app.graphic,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Column(Modifier.padding(6.dp)) {
                Surface(color = Color.Black.copy(0.6F)) {
                    Text(
                        text = app.name,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                Surface(color = Color.Black.copy(0.6F)) {
                    AppRating(
                        rating = app.rating,
                        Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun AppRating(rating: Double, color: Color, modifier: Modifier = Modifier) {
    val noRating = "- -"
    val textContentDescription = if (rating > 0) "app rating: $rating" else "no rating"
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Icon(Icons.Default.Star, null, tint = color)
        Text(
            text = if (rating > 0) rating.toString() else noRating,
            color = color,
            modifier = Modifier.semantics {
                this.contentDescription = textContentDescription
            }
        )
    }
}

@Composable
fun LocalTopAppsLazyRow(list: List<App>, listState: LazyListState, onItemClick: (App) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp, 0.dp),
        state = listState,
        modifier = Modifier.semantics { contentDescription = "Local top apps" }
    ) {
        items(list) { item ->
            LocalTopAppsRowItem(app = item) { onItemClick(item) }
        }
    }
}

@Composable
fun LocalTopAppsRowItem(app: App, onClick: () -> Unit) {
    val cardWidth = 135.dp
    val cardHeight = 200.dp
    val cardPadding = 12.dp
    val imageSize = cardWidth - cardPadding.times(2)
    Card(
        Modifier
            .clickable(onClickLabel = stringResource(id = R.string.action_open_app_detail)) { onClick() }
            .size(cardWidth, cardHeight),
        elevation = CardDefaults
            .cardElevation(6.dp)
    ) {
        Column(
            Modifier
                .padding(cardPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GlideImage(
                app.icon, Modifier
                    .clip(RoundedCornerShape(10))
                    .size(imageSize),
                contentScale = ContentScale.FillWidth
            )
            Text(text = app.name, minLines = 2, maxLines = 2, overflow = TextOverflow.Ellipsis)
            AppRating(rating = app.rating, Color.Black)
        }
    }
}

@Composable
fun ShowLoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowErrorMessage(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, style = MaterialTheme.typography.displayMedium)
    }
}
