package com.example.sensimate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

//Make the different available routes for the main screen, and bottom bar items.
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object MyEvents : BottomBarScreen(
        route = "MyEvents",
        title = "MyEvents",
        icon = Icons.Filled.Favorite
    )

    object Discover : BottomBarScreen(
        route = "Discover",
        title = "Discover",
        icon = Icons.Filled.Home
    )

    object Profile : BottomBarScreen(
        route = "Profile",
        title = "Profile",
        icon = Icons.Filled.Person
    )

    object EventPage : BottomBarScreen(
        route = "EventPage",
        title = "EventPage",
        icon = Icons.Filled.Done
    )

    object EventPage2 : BottomBarScreen(
        route = "EventPage2",
        title = "EventPage2",
        icon = Icons.Filled.Done
    )

    object EventManagerPage : BottomBarScreen(
        route = "EventManagerPage",
        title = "EventManagerPage",
        icon = Icons.Filled.Done
    )

    object ManageEventPage : BottomBarScreen(
        route = "ManageEventPage",
        title = "ManageEventPage",
        icon = Icons.Filled.Done
    )

    object ManageSurveyPage : BottomBarScreen(
        route = "ManageSurveyPage",
        title = "ManageSurveyPage",
        icon = Icons.Filled.Done
    )

    object Survey : BottomBarScreen(
        route = "SurveyPage",
        title = "SurveyPage",
        icon = Icons.Filled.Send
    )

    object SurveyScreen : BottomBarScreen(
        route = "SurveyScreen",
        title = "SurveyScreen",
        icon = Icons.Filled.Send
    )

    object ManageQuestionPage : BottomBarScreen(
        route = "ManageQuestionPage",
        title = "ManageQuestionPage",
        icon = Icons.Filled.Send
    )

    object EditEvent : BottomBarScreen(
        route = "EditEventPage",
        title = "EditEventPage",
        icon = Icons.Filled.Send
    )

}
