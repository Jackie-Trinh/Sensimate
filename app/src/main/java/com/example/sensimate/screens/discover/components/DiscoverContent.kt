package com.example.sensimate.screens.discover.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.repository.Events

@Composable
fun DiscoverContent(
    padding: PaddingValues,
    events: Events,
    deleteSurvey: (event: Event) -> Unit,
    navigateToUpdateSurveyScreen: (surveyId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = events
        ) { survey ->
            SurveyCard(
                event = survey,
                deleteSurvey = {
                    deleteSurvey(survey)
                },
                navigateToUpdateSurveyScreen = navigateToUpdateSurveyScreen
            )
        }
    }
}