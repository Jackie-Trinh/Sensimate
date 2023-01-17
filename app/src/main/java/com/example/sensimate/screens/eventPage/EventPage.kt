package com.example.sensimate.screens.eventPage

import android.content.ClipData.Item
import android.graphics.ColorSpace.Model
import android.net.Uri
import android.widget.StackView
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
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
            //alt bliver defineret i et column så, at item's bliver sat under hinanden.
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                //horizontalAlignment = Alignment.,
            ) {
                //Definere hvor titel tekst og Deltager knappen bliver sat ind på siden. Gør at de står ved siden af hinanden.
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
                        //så at Deltag Widgetet står i et kort der afspejler billedet.
                        Card(
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(
                                topStart = 29.dp,
                                topEnd = 29.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 31.dp),
                            elevation = 15.dp,
                        ) {
                            //Knap til at deltage i event.
                            SurveyButton(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            ) {
                                navController.navigate(route = BottomBarScreen.Survey.route)
                            }
                            // Drop shadow af ikon tekst
                            Text(
                                text = "Spørge",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.black),
                                modifier = Modifier
                                    .absoluteOffset(x = 0.dp, y = 23.dp)
                                    .alpha(0.25f)
                                    .blur(2.dp)
                                    .offset(1.dp, 2.dp))
                            //Ikon tekst
                            Text(
                                text = "Spørge",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .absoluteOffset(x=0.dp,y=23.dp)
                            )
                        }
                    }

                }
                //definere at der er mellemrum mellem Event info og Rubrik.
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp
                    )
                ) {
                    Text(text = viewModel.event.date, fontSize = 16.sp)
                    Text(text = "Beskrivelse", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = viewModel.event.description, fontSize = 16.sp)
                }
            }
        }
    }
}

//En funktion der definere hvordan billederne bliver loadet.
@Composable
private fun SurveyButton(
    modifier: Modifier,
    navController: NavController,
    onClick: ()->Unit

    ) {
    Box(modifier = modifier
        .clickable { onClick }
        .fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.survey_icon)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.survey_icon),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxSize(1f)
        )
    }

}

//en funktion, der definere hvordan man swiper igennem en liste af billeder.
// Disse billeder bliver instantieret ved hjælp af en mængde items, som allesammen indeholder et billede.
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    ExperimentalSwipeableCardApi::class
)
@Composable
private fun Swipe(
) {
    val states = pictures.reversed()
        .map { it to rememberSwipeableCardState() }
    Row(modifier = Modifier
        .padding(horizontal = 24.dp, vertical = 12.dp),
    )
    {
        states.forEach { (album, state) ->
            if (state.swipedDirection == null) {
                ImageCard(modifier = Modifier
                    .swipableCard(
                        state = state,
                        onSwiped = { direction ->
                            println("The card was swiped to $direction")
                        },
                        onSwipeCancel = {
                            println("The swiping was cancelled")
                        }
                    ), album = album)
                }
            LaunchedEffect(album, state.swipedDirection) {
                if (state.swipedDirection != null) {

                }
            }
        }
    }
}

//En funktion der deffinere hvordan billederne bliver hentet.
// Lige nu er de hardcoded, men ved hjælp af en data-klasse, ville man kunne hente dem fra en Database.
@Composable
private fun ImageCard(
    modifier: Modifier,
    album: Album,
) {
    Card(modifier
        .size(500.dp,200.dp),
        elevation = 10.dp) {
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier=Modifier,
                contentScale = ContentScale.Crop,
                painter = painterResource(album.drawableResId),
                contentDescription = null,
                alignment = Alignment.Center

            )
        }
    }
}

data class Album(
    @DrawableRes val drawableResId: Int)

val pictures = listOf(
    Album(R.drawable.survey_icon),
    Album(R.drawable.ic_sensimate),
    Album(R.drawable.bar_image),
)