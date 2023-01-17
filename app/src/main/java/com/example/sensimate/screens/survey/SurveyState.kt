package com.example.sensimate.screens.survey

import androidx.annotation.StringRes
import androidx.compose.runtime.*
import com.example.sensimate.model2.Question

@Stable
class QuestionState(
    val question: Question,
    val questionIndex: Int,
    val totalQuestionsCount: Int,
    val showPrevious: Boolean,
    val showDone: Boolean
) {
    var enableNext by mutableStateOf(false)
//    var answer by mutableStateOf<Answer<*>?>(null)
    //is int
    var answer by mutableStateOf(null)
}

data class SurveyState (
    val surveyTitle: String = "",
    val questionsState: List<QuestionState>,
)  {
    var currentQuestionIndex by mutableStateOf(0)
}

//open class SurveyState {
//    data class Questions(
//        val surveyTitle: String,
//        val questionsState: List<QuestionState>
//    ) : SurveyState(), MutableState<SurveyState> {
//        var currentQuestionIndex by mutableStateOf(0)
//    }
//
//}