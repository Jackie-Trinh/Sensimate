package com.example.sensimate.screens.discover

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.screens.discover.components.AddSurveyAlertDialog
import com.example.sensimate.screens.discover.components.AddSurveyFloatingActionButton
import com.example.sensimate.screens.discover.components.DiscoverContent


@Composable
fun Discover(
    navController: NavController,
    viewModel: DiscoverViewModel = hiltViewModel(),
    navigateToUpdateSurveyScreen: (surveyId: Int) -> Unit,
) {
    val surveys by viewModel.surveys.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        content = { padding ->
            DiscoverContent(
                padding = padding,
                surveys = surveys,
                deleteSurvey = { survey ->
                    viewModel.deleteSurvey(survey)
                },
                navigateToUpdateSurveyScreen = navigateToUpdateSurveyScreen
            )
            AddSurveyAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog()
                },
                addSurvey = { survey ->
                    viewModel.addSurvey(survey)
                }
            )
        },
        floatingActionButton = {
            AddSurveyFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )


//    //text data for user events
//    val events = listOf(
//        com.example.sensimate.data.Event(
//            3,
//            "Event 1",
//            "Desc 1",
//            "06-01-2023",
//            "H.C Andersens vej 3",
//            listOf(
//                com.example.sensimate.data.EventItem(
//                    1,
//                    "item 1",
//                    "desc 1",
//                    "06-01-2023",
//                    "H.C Andersens vej 3"
//                )
//            )
//        ),
//        com.example.sensimate.data.Event(
//            2,
//            "Event 2",
//            "Desc 2",
//            "06-01-2023",
//            "H.C Andersens vej 3",
//            listOf(
//                com.example.sensimate.data.EventItem(
//                    2,
//                    "item 2",
//                    "desc 2",
//                    "06-01-2023",
//                    "H.C Andersens vej 3"
//                )
//            )
//        ),
//        com.example.sensimate.data.Event(
//            3,
//            "Event 3",
//            "Desc 3",
//            "06-01-2023",
//            "H.C Andersens vej 3",
//            listOf(
//                com.example.sensimate.data.EventItem(
//                    3,
//                    "item 3",
//                    "desc 3",
//                    "06-01-2023",
//                    "H.C Andersens vej 3"
//                )
//            )
//        )
//    )
//
//    EventCardSelection(navController, events)

}


