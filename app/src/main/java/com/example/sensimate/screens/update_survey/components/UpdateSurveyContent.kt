package com.example.sensimate.presentation.update_survey.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sensimate.core.Constants.Companion.AUTHOR
import com.example.sensimate.core.Constants.Companion.SURVEY_TITLE
import com.example.sensimate.core.Constants.Companion.UPDATE
import com.example.sensimate.domain.model.Survey

@Composable
fun UpdateSurveyContent(
    padding: PaddingValues,
    survey: Survey,
    updateTitle: (title: String) -> Unit,
    updateAuthor: (author: String) -> Unit,
    updateSurvey: (survey: Survey) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = survey.title,
            onValueChange = { title ->
                updateTitle(title)
            },
            placeholder = {
                Text(
                    text = SURVEY_TITLE
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = survey.author,
            onValueChange = { author ->
                updateAuthor(author)
            },
            placeholder = {
                Text(
                    text = AUTHOR
                )
            }
        )
        Button(
            onClick = {
                updateSurvey(survey)
                navigateBack()
            }
        ) {
            Text(
                text = UPDATE
            )
        }
    }
}