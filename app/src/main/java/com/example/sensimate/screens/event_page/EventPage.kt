package com.example.sensimate.screens.event_page

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import com.example.sensimate.R.string as AppText


import androidx.compose.material3.*
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.example.sensimate.R
import com.example.sensimate.core.Constants.Companion.EVENT_ID

import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.edit_event.BasicIconButtonWithText
import com.example.sensimate.screens.edit_event.BasicVectorIconButtonWithText
import com.example.sensimate.ui.theme.LButton1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventPage(
    viewModel: EventPageViewModel = hiltViewModel(),
    eventId: String,
    navController: NavController
) {
    val event by viewModel.event

    val userData by viewModel.userData

    LaunchedEffect(Unit) { viewModel.initialize(eventId) }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { },

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    if (userData.admin) {
                        Row(modifier = Modifier
                            .padding(end = 10.dp)
                            .fillMaxHeight()
                            .clickable {
                                navController.navigate(
                                    "${BottomBarScreen.EditEvent.route}?$EVENT_ID={${event.eventId}}"
                                )
                            },
                            verticalAlignment = Alignment.CenterVertically,

                        ) {
                            Text(
                                text = "Edit Event",
                            )
                        }
                    }
                }
            )
        },

        content = { paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {


                LazyColumn(Modifier.fillMaxSize()) {

                    item {
                        Image(
                            painter = rememberAsyncImagePainter(event.eventImage),
                            contentDescription = "event image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 0.dp, vertical = 0.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column() {
                                    Text(
                                        text = event.title,
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.width(280.dp)
                                    )

                                    Text(text = event.date, fontSize = 16.sp)

                                    Spacer(modifier = Modifier.padding(vertical = 6.dp))

                                    Text(text = event.address, fontSize = 16.sp)

                                    Spacer(modifier = Modifier.padding(vertical = 12.dp))

                                    Text(
                                        text = "Beskrivelse:",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.padding(vertical = 12.dp))

                                }
                                //Pictures
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ){

                                        LikeAnim()

                                        IconButton(
                                            onClick = { navController.navigate("${BottomBarScreen.SurveyScreen.route}?$EVENT_ID={${event.eventId}}") })
                                        {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(R.drawable.survey_icon)
                                                    .crossfade(true)
                                                    .build(),
                                                placeholder = painterResource(id = R.drawable.survey_icon),
                                                contentDescription = "",
                                            )
                                        }
                                        Text(
                                            text = stringResource(id = AppText.survey),
                                            style = MaterialTheme.typography.titleSmall,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .offset(y = (-2).dp)
                                        )
                                    }





//                                    Card(
//                                        modifier = Modifier.size(64.dp),
//                                        shape = RoundedCornerShape(
//                                            topStart = 29.dp,
//                                            topEnd = 29.dp,
//                                            bottomStart = 0.dp,
//                                            bottomEnd = 31.dp
//                                        ),
//
//                                    ) {
//                                        //Knap til at deltage i event.
//                                        SurveyButton(
//                                            modifier = Modifier.size(200.dp),
//                                            navController = navController
//                                        ) {
//                                            navController.navigate("${BottomBarScreen.SurveyScreen.route}?$EVENT_ID={${event.eventId}}")
//                                        }
//                                        // Drop shadow af ikon tekst
//                                        Text(
//                                            text = "Deltag",
//                                            fontWeight = FontWeight.Bold,
//                                            textAlign = TextAlign.Center,
//                                            color = colorResource(id = R.color.black),
//                                            modifier = Modifier
//                                                .absoluteOffset(x = 0.dp, y = 23.dp)
//                                                .alpha(0.25f)
//                                                .blur(2.dp)
//                                                .offset(1.dp, 2.dp)
//                                        )
//                                        //Ikon tekst
//                                        Text(
//                                            text = "Deltag",
//                                            fontWeight = FontWeight.Bold,
//                                            textAlign = TextAlign.Center,
//                                            modifier = Modifier
//                                                .absoluteOffset(x = 0.dp, y = 23.dp)
//                                        )
//                                    }


                            }
                            Text(text = event.description, fontSize = 16.sp)




                        }

                    }


                }

            }
        }
    )
//    LazyColumn(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colors.background),
//        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
//    ) {
//        item {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(MaterialTheme.colors.surface)
//                    .padding(10.dp, 20.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row(modifier = Modifier
//                    .fillMaxHeight()
//                    .clickable { navController.popBackStack() }
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
//                        contentDescription = "back arrow"
//                    )
//                    Text(text = "Back")
//                }
//
////               Row(modifier = Modifier
////                   .fillMaxHeight()
////                   .clickable {  navController.navigate("${BottomBarScreen.Survey.route}/${eventId}") }
////               ){
////                   Text(
////                       text = "Survey",
////                   )
////               }
//
//                if (userData.isAdmin) {
//                    Row(modifier = Modifier
//                        .fillMaxHeight()
//                        .clickable { navController.navigate("${BottomBarScreen.EditEvent.route}?$EVENT_ID={${event.eventId}}") }
//                    ) {
//                        Text(
//                            text = "Edit Event",
//                        )
//                    }
//                }
//
//            }
//        }


//        item {
//            //alt bliver defineret i et column så, at item's bliver sat under hinanden.
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colors.surface)
//                    .padding(horizontal = 24.dp, vertical = 12.dp)
//                //horizontalAlignment = Alignment.,
//            ) {
//                //Definere hvor titel tekst og Deltager knappen bliver sat ind på siden. Gør at de står ved siden af hinanden.
//                Row(
//                    modifier = Modifier
//                        .background(MaterialTheme.colors.surface)
//                        .fillMaxSize(),
//                    horizontalArrangement = Arrangement.spacedBy(125.dp)
//                ) {
//                    Text(
//                        text = event.title,
//                        fontSize = 28.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//
//
//                }
//                //definere at der er mellemrum mellem Event info og Rubrik.
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(
//                        12.dp
//                    )
//                ) {
//                    Text(text = event.date, fontSize = 16.sp)
//                    Text(text = "Beskrivelse", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                    Text(text = event.description, fontSize = 16.sp)
//                }
//            }
//        }
//    }
}

@Composable
private fun LikeAnim() {
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(false)
    }

    val scale = remember {
        Animatable(1f)
    }

    Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Follow Event",
        tint = if (enabled) LButton1 else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(40.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                enabled = !enabled
                coroutineScope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100)
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100)
                    )
                }
            }
    )

}




//En funktion der definere hvordan billederne bliver loadet.
@Composable
private fun SurveyButton(
    modifier: Modifier,
    navController: NavController,
    onClick: () -> Unit

) {
    Box(modifier = modifier
        .clickable { onClick() }
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
                .background(MaterialTheme.colorScheme.background)

        )
    }

}

//en funktion, der definere hvordan man swiper igennem en liste af billeder.
// Disse billeder bliver instantieret ved hjælp af en mængde items, som allesammen indeholder et billede.
//@OptIn(
//    ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
//    ExperimentalSwipeableCardApi::class
//)
@Composable
private fun Swipe(
    imageUrl: String,
) {
//    val states = pictures.reversed()
//        .map { it to rememberSwipeableCardState() }
//    Row(
//        modifier = Modifier
//            .padding(3.dp),
//    )
//    {
//        states.forEach { (album, state) ->
//            if (state.swipedDirection == null) {
//                ImageCard(modifier = Modifier
//                    .swipableCard(
//                        state = state,
//                        onSwiped = { direction ->
//                            println("The card was swiped to $direction")
//                        },
//                        onSwipeCancel = {
//                            println("The swiping was cancelled")
//                        }
//                    ), album = album,
//                    imageUrl = imageUrl
//                )
//            }
//            LaunchedEffect(album, state.swipedDirection) {
//                if (state.swipedDirection != null) {
//
//                }
//            }
//        }
//    }
}

//En funktion der deffinere hvordan billederne bliver hentet.
// Lige nu er de hardcoded, men ved hjælp af en data-klasse, ville man kunne hente dem fra en Database.
@Composable
private fun ImageCard(
//    modifier: Modifier,
//    album: Album,
    imageUrl: String,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .size(400.dp, 200.dp),
//        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
//                modifier = Modifier.size(128.dp)
            )
        }
    }
}

data class Album(
    @DrawableRes val drawableResId: Int
)

val pictures = listOf(
    Album(R.drawable.survey_icon),
    Album(R.drawable.ic_sensimate),
    Album(R.drawable.bar_image),
)
