package com.omersungur.composetraining.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.omersungur.composetraining.R
import com.omersungur.composetraining.model.BestHouse
import com.omersungur.composetraining.model.Category
import com.omersungur.composetraining.model.RecommendationHouse
import com.omersungur.composetraining.ui.theme.ComposeTrainingTheme
import com.omersungur.composetraining.util.Dimensions.spacing_s
import com.omersungur.composetraining.util.bestHouseList
import com.omersungur.composetraining.util.categoryList
import com.omersungur.composetraining.util.recommendationList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrainingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var filteredRecommendationList by remember { mutableStateOf(recommendationList) }

    Column {
        Search { str ->
            filteredRecommendationList = recommendationList.filter {
                it.name?.contains(str, ignoreCase = true) ?: false
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(categoryList) {
                CategoryRow(it)
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        OptionsRow()

        LazyRow {
            items(bestHouseList) {
                BestHouseRow(bestHouse = it)
            }
        }

        RecommendationTextView()

        LazyColumn {
            items(filteredRecommendationList) { recommended ->
                RecommendationCard(recommendation = recommended)
            }
        }
    }
}

@Composable
fun Search(
    onValueChange: (value: String) -> Unit,
) {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 25.dp),
        placeholder = {
            Text(text = "Search")
        },
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun CategoryRow(category: Category) {
    Button(
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        onClick = {
            // sonar - comment
        },
    ) {
        Text(text = category.name)
    }
}

@Composable
fun OptionsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Best for you",
            fontSize = 16.sp,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Text(
            modifier = Modifier.padding(12.dp),
            text = "View All",
            color = Color.Blue,
        )
    }
}

@Composable
fun BestHouseRow(bestHouse: BestHouse) {
    Card(
        modifier = Modifier
            .size(300.dp)
            .padding(8.dp)
            .background(Color.White)
            .clickable {
                // sonar - comment
            },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(bestHouse.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "OK",
                contentScale = ContentScale.FillBounds,
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom

            ) {
                Text(text = bestHouse.name, color = Color.White)

                Text(text = bestHouse.address, color = Color.White)
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp),
                    imageVector = Icons.Outlined.Favorite,
                    tint = Color.White,
                    contentDescription = "Favorite"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = Color.Yellow,
                        contentDescription = "Favorite"
                    )
                    Spacer(modifier = Modifier.padding(end = 8.dp))
                    Text(text = bestHouse.star.toString())
                }
            }
        }
    }
}

@Composable
fun RecommendationTextView() {
    Text(
        modifier = Modifier.padding(8.dp),
        text = "Recommendations",
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
    )
}

@Composable
fun RecommendationCard(modifier: Modifier = Modifier, recommendation: RecommendationHouse) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(spacing_s),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recommendation.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.content_description_image),
                contentScale = ContentScale.Fit,
            )

            Column(modifier = Modifier.padding(start = 4.dp)) {
                Text(
                    text = recommendation.name.orEmpty(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(text = recommendation.address.orEmpty(), fontSize = 14.sp)

                Row {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = Color.Yellow,
                        contentDescription = "Favorite",
                    )

                    Text(
                        text = recommendation.star.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
