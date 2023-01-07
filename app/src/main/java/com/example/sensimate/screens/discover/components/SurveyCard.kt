package com.example.sensimate.screens.discover.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sensimate.domain.model.Survey


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SurveyCard(
    survey: Survey,
    deleteSurvey: () -> Unit,
    navigateToUpdateSurveyScreen: (surveyId: Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
        onClick = {
            navigateToUpdateSurveyScreen(survey.id)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                TextTitle(
                    surveyTitle = survey.title
                )
                TextAuthor(
                    surveyAuthor = survey.author
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            DeleteIcon(
                deleteSurvey = deleteSurvey
            )
        }
    }
}