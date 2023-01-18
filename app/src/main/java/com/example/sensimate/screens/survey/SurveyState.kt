package com.example.sensimate.screens.survey

import androidx.compose.runtime.*
import com.example.sensimate.model2.Question

@Stable
class QuestionState(
    var question: MutableState<Question>,
    var questionIndex: Int,
    var totalQuestionsCount: Int,
    var showPrevious: Boolean,
    var showDone: Boolean
) {
    var enableNext by mutableStateOf(false)
    var answered by mutableStateOf(String)
}

data class SurveyState (
    var surveyTitle: String = "",
    var questionsStates: List<QuestionState>,
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
