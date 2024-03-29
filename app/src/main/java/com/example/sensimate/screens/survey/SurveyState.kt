package com.example.sensimate.screens.survey

import androidx.compose.runtime.*
import com.example.sensimate.firebase_model.data.Question


data class QuestionState(
    var question: MutableState<Question> = mutableStateOf(Question()) ,
    var questionIndex: Int = 0,
    var showPrevious: Boolean = false,
    var showDone: Boolean = false,
    var enableNext: Boolean = false,
    var answeredString: String = "",
    var answeredOption: List<String> = emptyList()
)

data class SurveyState (
    var surveyTitle: String = "",
    var questionsStates: MutableList<QuestionState>,
    var currentQuestionIndex: Int = 0,
)

sealed class PossibleAnswerType {
    data class SingleChoice(val optionsStringRes: List<Int>) : PossibleAnswerType()
//    data class SingleChoiceIcon(val optionsStringIconRes: List<Pair<Int, Int>>) : PossibleAnswerType()
    data class MultipleChoice(val optionsStringRes: List<Int>) : PossibleAnswerType()
//    data class MultipleChoiceIcon(val optionsStringIconRes: List<Pair<Int, Int>>) : PossibleAnswerType()

    data class Slider(
        val range: ClosedFloatingPointRange<Float>,
        val steps: Int,
        val startText: String,
        val endText: String,
        val neutralText: String,
        val defaultValue: Float = 5.5f
    ) : PossibleAnswerType()
}

sealed class Answer<T : PossibleAnswerType> {
    object PermissionsDenied : Answer<Nothing>()
    data class SingleChoice(val answer: String) : Answer<PossibleAnswerType.SingleChoice>()
    data class MultipleChoice(val answersStringRes: Set<String>) :
        Answer<PossibleAnswerType.MultipleChoice>()

    data class Slider(val answerValue: Float) : Answer<PossibleAnswerType.Slider>()
}

fun Answer.MultipleChoice.withAnswerSelected(
    answer: String,
    selected: Boolean
): Answer.MultipleChoice {
    val newStringRes = answersStringRes.toMutableSet()
    if (!selected) {
        newStringRes.remove(answer)
    } else {
        newStringRes.add(answer)
    }
    return Answer.MultipleChoice(newStringRes)
}
