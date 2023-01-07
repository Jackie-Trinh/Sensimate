package com.example.sensimate.screens.update_survey

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sensimate.presentation.update_survey.components.UpdateSurveyContent
import com.example.sensimate.presentation.update_survey.components.UpdateSurveyTopBar
import com.example.sensimate.screens.discover.DiscoverViewModel


@Composable
fun UpdateSurveyScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    surveyId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getSurvey(surveyId)
    }
    Scaffold(
        topBar = {
            UpdateSurveyTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            UpdateSurveyContent(
                padding = padding,
                survey = viewModel.survey,
                updateTitle = { title ->
                    viewModel.updateTitle(title)
                },
                updateAuthor = { author ->
                    viewModel.updateAuthor(author)
                },
                updateSurvey = { survey ->
                    viewModel.updateSurvey(survey)
                },
                navigateBack = navigateBack
            )
        }
    )
}