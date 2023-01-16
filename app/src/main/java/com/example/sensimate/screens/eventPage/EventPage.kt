package com.example.sensimate.screens.eventPage

import android.content.ClipData.Item
import android.graphics.ColorSpace.Model
import android.net.Uri
import android.widget.StackView
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.sensimate.R
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.eventManager.EventManagerViewModel
import java.net.URI
import java.time.format.TextStyle
import java.util.Stack
import kotlin.time.Duration.Companion.seconds

@Composable
fun EventPage(
    viewModel: EventManagerViewModel = hiltViewModel(),
    eventId: Int,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getEvent(eventId)
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
    ) {
        item { 
           Row(modifier = Modifier
               .fillMaxWidth()
               .background(MaterialTheme.colors.surface)
               .padding(10.dp, 20.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement  =  Arrangement.SpaceBetween
               ){
               Row(modifier = Modifier
                   .fillMaxHeight()
                   .clickable { navController.popBackStack() }
               ){
                   Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                       contentDescription = "back arrow")
                   Text(text = "Back")
               }
               Row(modifier = Modifier
                   .fillMaxHeight()
                   .clickable { navController.navigate("${BottomBarScreen.ManageEventPage.route}/${eventId}") }
               ){
                   Text(
                       text = "Edit Event",
                   )
               }
           }
        }

        item {
            Swipe()
        }


        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                //horizontalAlignment = Alignment.,
            ) {
                Row(
                        modifier = Modifier
                            .background(MaterialTheme.colors.surface)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(125.dp)
                    ) {
                    Text(
                        text = viewModel.event.title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box() {
                        Card(
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(5.dp),
                            elevation = 15.dp,
                        ) {
                            SurveyButton(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            ) {
                                navController.navigate(route = BottomBarScreen.Survey.route)
                            }
                            Text(
                                text = "Survey",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .absoluteOffset(y=25.dp))
                        }
                    }

                }
                    Text(text = viewModel.event.date, fontSize = 16.sp)
                    Text(text = "Beskrivelse", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = viewModel.event.description, fontSize = 16.sp)

            }
        }
    }
}

@Composable
private fun SurveyButton(
    modifier: Modifier,
    navController: NavController,
    onClick: ()->Unit

    ) {
    Box(modifier = modifier
        .clickable { onClick }
        .width(75.dp)
        .height(70.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.survey_icon)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.survey_icon),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxSize(1f)
        )
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun Swipe(
) {
    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
    LazyRow(
        flingBehavior = flingBehavior,
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .swipeable(
                state = rememberSwipeableState(0),
                anchors = mapOf(0f to 0, 1f to 1),
                orientation = Orientation.Horizontal,
                thresholds = { _, _ -> FractionalThreshold(0f) }
            )
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),

        verticalAlignment = Alignment.CenterVertically

    ) {
        items(2) {item-> ImageCard()}
    }
}


@Composable
private fun ImageCard(
) {
    Row(modifier = Modifier.fillMaxSize(),

    ) {
        Card(modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp)
        { Box (modifier = Modifier,
        contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.bar_image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.bar_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            }
        }
    }

}