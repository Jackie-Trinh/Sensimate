package com.example.sensimate.screens.survey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.sensimate.core.Constants
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.Question
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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


    var editMode by mutableStateOf(false)


    //Get questions and event
    fun initialize(eventId: String) {
        launchCatching {
            if (eventId != Constants.EVENT_DEFAULT_ID) {
                questions = storageService.getQuestionsForEvent(eventId.idFromParameter())
                event.value = storageService.getEvent(eventId.idFromParameter()) ?: Event()
            }


            //If making new survey, add empty question
            if (questions.isEmpty()){
                questions.add(
                    Question(
                    questionId = "",
                    questionText = "",
                    questionType = "Single choice",
                    answerOptions = mutableListOf("")
                )
                )
                editMode = true

            }
            //Put questions into questions state
            val questionsState: List<QuestionState> = questions.mapIndexed { index, question ->
                val showPrevious = index > 0
                val showDone = index == questions.size - 1
                val tempQuestion = mutableStateOf(Question())
                tempQuestion.value = question

                QuestionState(
                    question = tempQuestion,
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

    fun onPressEditButton() {
        editMode = !editMode
    }


    fun onQuestionTextChange(newValue: String) {
        surveyState.value.questionsStates[surveyState.value.currentQuestionIndex].question.value =
            surveyState.value.questionsStates[surveyState.value.currentQuestionIndex].question.value
                .copy(questionText = newValue)
    }

    fun onAnswerOptionsChange(newValue: List<String>) {
        surveyState.value.questionsStates[surveyState.value.currentQuestionIndex].question.value =
            surveyState.value.questionsStates[surveyState.value.currentQuestionIndex].question.value
                .copy(answerOptions = newValue)
    }

    fun onAddQuestionClick() {



        questions.add(questions[surveyState.value.currentQuestionIndex].copy(questionText = "Question", questionId = ""))


        val questionsState: List<QuestionState> = questions.mapIndexed { index, question ->
            val showPrevious = index > 0
            val showDone = index == questions.size - 1
            val tempQuestion = mutableStateOf(Question())
            tempQuestion.value = question

            QuestionState(
                question = tempQuestion,
                questionIndex = index,
                totalQuestionsCount = questions.size,
                showPrevious = showPrevious,
                showDone = showDone,
            )
        }

        surveyState.value = surveyState.value
            .copy(surveyTitle = event.value.title, questionsStates = questionsState)

        surveyState.value = surveyState.value.copy(currentQuestionIndex = questions.size-1)
    }


    fun onDoneEditing() {
        launchCatching {

            surveyState.value.questionsStates.forEach { questionState ->

                if (questionState.question.value.questionId.isBlank()) {
                    storageService.saveQuestion(
                        eventId = event.value.eventId,
                        question = questionState.question.value
                    )
                } else {
                    storageService.updateQuestion(
                        eventId = event.value.eventId,
                        question = questionState.question.value
                    )
                }
            }
        }
    }


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