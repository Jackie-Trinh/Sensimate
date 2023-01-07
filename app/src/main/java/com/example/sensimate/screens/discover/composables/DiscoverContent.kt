package com.example.sensimate.screens.discover.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sensimate.domain.model.Survey
import com.example.sensimate.domain.repository.Surveys

@Composable
fun DiscoverContent(
    padding: PaddingValues,
    surveys: Surveys,
    deleteSurvey: (survey: Survey) -> Unit,
    navigateToUpdateSurveyScreen: (surveyId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = surveys
        ) { survey ->
            SurveyCard(
                survey = survey,
                deleteSurvey = {
                    deleteSurvey(survey)
                },
                navigateToUpdateSurveyScreen = navigateToUpdateSurveyScreen
            )
        }
    }
}