package com.example.sensimate.screens.survey

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.sensimate.core.Constants
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.model2.Event
import com.example.sensimate.model2.Question
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.ui.res.stringResource

@HiltViewModel
class SurveyViewModel @Inject constructor(
//    logService: LogService,
    private val storageService: StorageService,
) : SensiMateViewModel() {


    //Survey of this event
    val event = mutableStateOf(Event())

    //Questions of survey
    var questions = mutableListOf<Question>()

    //Survey state with questions
    var surveyState = mutableStateOf(SurveyState("", emptyList()))

    //List of possible answers
//    var answerType = PossibleAnswerType


    //Get questions and event
    fun initialize(eventId: String) {
        launchCatching {
            if (eventId != Constants.EVENT_DEFAULT_ID) {
                questions = storageService.getQuestionsForEvent(eventId.idFromParameter())
                event.value = storageService.getEvent(eventId.idFromParameter()) ?: Event()
            }

            //Put questions into questions state
            val questionsState: List<QuestionState> = questions.mapIndexed { index, question ->
                val showPrevious = index > 0
                val showDone = index == questions.size - 1

//                if (question.questionType == "single_choice") {
//
//                }

                QuestionState(
                    question = question,
                    questionIndex = index,
                    totalQuestionsCount = questions.size,
                    showPrevious = showPrevious,
                    showDone = showDone,
                )


            }
            surveyState.value =
                SurveyState(surveyTitle = event.value.title, questionsStates = questionsState)

        }
    }

    fun increaseCurrentQuestionIndex(currentQuestionIndex: Int){
        surveyState.value = surveyState.value.copy(currentQuestionIndex = currentQuestionIndex+1)
    }

    fun decreaseCurrentQuestionIndex(currentQuestionIndex: Int){
        surveyState.value = surveyState.value.copy(currentQuestionIndex = currentQuestionIndex-1)
    }



    var editMode = mutableStateOf(false)

    fun onPressEditButton() {
        if (editMode.value) {
            editMode to false
        } else {
            editMode to true
        }
    }




//    fun onAddQuestionClick() {
//        launchCatching {
//            val editedQuestion = Question()
//            if (editedQuestion.questionId.isBlank()) {
//                storageService.saveQuestion(eventId = event.value.eventId, editedQuestion)
//            } else {
//                storageService.updateQuestion(event.value.eventId, editedQuestion)
//            }
//            storageService.saveQuestion(eventId = event.value.eventId, editedQuestion)
//        }
//    }


//
//    fun onBackClick(popUpScreen: () -> Unit) {
//        launchCatching {
//
//            popUpScreen()
//        }
//    }
//
//    fun onDoneClick(popUpScreen: () -> Unit) {
//        launchCatching {
//            val editedTask = event.value
//            if (editedTask.eventId.isBlank()) {
//                storageService.saveEvent(editedTask)
//            } else {
//                storageService.updateEvent(editedTask)
//            }
//            popUpScreen()
//        }
//    }


    private fun Int.toClockPattern(): String {
        return if (this < 10) "0$this" else "$this"
    }

    companion object {
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
    }
}