package com.example.sensimate.screens.survey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sensimate.core.Constants
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.Question
import com.example.sensimate.firebase_model.data.UserAnswer
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
//    logService: LogService,
    private val storageService: StorageService,
    private val accountService: AccountService,
) : SensiMateViewModel() {

    var userData = mutableStateOf(UserData())


    //Survey of this event
    val event = mutableStateOf(Event())

    //Questions of survey
    private var questions = mutableListOf<Question>()


    //Survey state with questions
    var surveyState = mutableStateOf(SurveyState("", mutableListOf(QuestionState())))


    var editMode by mutableStateOf(false)


    //Get questions and event
    fun initialize(eventId: String) {
        launchCatching {
            userData.value = storageService.getUserData(accountService.currentUserId)!!

            if (eventId != Constants.EVENT_DEFAULT_ID) {

                event.value = storageService.getEvent(eventId.idFromParameter()) ?: Event()

                questions = storageService.getQuestionsForEvent(eventId.idFromParameter())



                if (questions.isEmpty()){
                    questions.add(Question(answerOptions = listOf("")))
                }
            }



            //Put questions into questions state
            val questionsState: List<QuestionState> = questions.mapIndexed { index, question ->
                val showPrevious = index > 0
                val showDone = index == questions.size - 1

                QuestionState(
                    question = mutableStateOf(question),
                    questionIndex = index,
                    showPrevious = showPrevious,
                    showDone = showDone,
                )

            }
            //set SurveyState
            surveyState.value =
                SurveyState(
                    surveyTitle = event.value.title,
                    questionsStates = questionsState as MutableList<QuestionState>
                )

        }
    }

    fun increaseCurrentQuestionIndex(currentQuestionIndex: Int) {
        surveyState.value = surveyState.value.copy(currentQuestionIndex = currentQuestionIndex + 1)
    }

    fun decreaseCurrentQuestionIndex(currentQuestionIndex: Int) {
        surveyState.value = surveyState.value.copy(currentQuestionIndex = currentQuestionIndex - 1)
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

    fun onAnswer(answer: String) {
        surveyState.value.questionsStates[surveyState.value.currentQuestionIndex] =
            surveyState.value.questionsStates[surveyState.value.currentQuestionIndex]
                .copy(answeredString = answer)

    }

    fun onDonePressed() {

        val answers = mutableListOf<String>()

        surveyState.value.questionsStates.forEach{questionState ->
            answers.add(questionState.answeredString)
        }

        //set SurveyState
        launchCatching {

        storageService.saveUserAnswer(
            eventId = event.value.eventId,
            UserAnswer(answers = answers, userId = userData.value.userId)
        )


        }

    }

    fun onAddQuestionClick(currentQuestionIndex: Int) {

        //previous last question is no longer last
        surveyState.value.questionsStates[currentQuestionIndex] =
            surveyState.value.questionsStates[currentQuestionIndex]
                .copy(showDone = false)
        //add new question with previous answer values
        surveyState.value.questionsStates.add(
            QuestionState(
                questionIndex = currentQuestionIndex + 1,
                showPrevious = true,
                showDone = true,
                question = mutableStateOf(
                    Question(
                        answerOptions = surveyState.value
                            .questionsStates[currentQuestionIndex]
                            .question.value.answerOptions
                    )
                )
            )
        )

        surveyState.value = surveyState.value.copy(currentQuestionIndex = currentQuestionIndex + 1)
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

//    fun onDeleteQuestion() {
//
//        surveyState.value.questionsStates[]
//
//        val questionsState: List<QuestionState> = surveyState.value.questionsStates.mapIndexed { index, question ->
//            val showPrevious = index > 0
//            val showDone = index == questions.size - 1
//
//            QuestionState(
//                question = mutableStateOf(question),
//                questionIndex = index,
//                showPrevious = showPrevious,
//                showDone = showDone,
//            )
//
//        }
//        //set SurveyState
//        surveyState.value =
//            SurveyState(
//                surveyTitle = event.value.title,
//                questionsStates = questionsState as MutableList<QuestionState>
//            )
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