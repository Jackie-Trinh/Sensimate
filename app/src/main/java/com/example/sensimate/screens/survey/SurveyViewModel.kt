package com.example.sensimate.screens.survey

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.core.Constants
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.domain.repository.EventRepository
import com.example.sensimate.domain.repository.Questions
import com.example.sensimate.model2.Event
import com.example.sensimate.model2.Question
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SurveyViewModel @Inject constructor(
//    logService: LogService,
    private val storageService: StorageService,
) : SensiMateViewModel() {
    //Survey uiState
    //TODO: user given answers aren't saved
//    private val _uiState = MutableLiveData<SurveyState>()
//    val uiState: LiveData<SurveyState>
//        get() = _uiState

    //Survey of this event
    val event = mutableStateOf(Event())

    //Questions of survey
    var questions = mutableListOf<Question>()

    lateinit var surveyInitialState: SurveyState
//    var surveyInitialState = mutableStateOf(SurveyState())

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
                QuestionState(
                    question = question,
                    questionIndex = index,
                    totalQuestionsCount = questions.size,
                    showPrevious = showPrevious,
                    showDone = showDone
                )
            }
            surveyInitialState = SurveyState(surveyTitle = event.value.title, questionsState =  questionsState)
//            _uiState.value = surveyInitialState

        }
    }

    var editMode = mutableStateOf(false)

    fun onPressEditButton() {
        if(editMode.value){
            editMode to false
        } else {
            editMode to true
        }
    }

    //current page we are at
    var currentPage =  1
    //new page checker
    var newPageAvailable = { mutableStateOf(true) }

    fun increaseCurrentPage() = viewModelScope.launch(Dispatchers.IO) {
        currentPage += 1
    }
    fun decreaseCurrentPage() = viewModelScope.launch(Dispatchers.IO) {
        currentPage -= 1
    }


    fun onAddQuestionClick() {
        launchCatching {
            val editedQuestion = Question()
//            if (editedQuestion.questionId.isBlank()) {
//                storageService.saveQuestion(eventId = event.value.eventId, editedQuestion)
//            } else {
//                storageService.updateQuestion(event.value.eventId, editedQuestion)
//            }
//            storageService.saveQuestion(eventId = event.value.eventId, editedQuestion)
        }
    }

//    fun getQuestionText(): String {
//        return questions.[currentPage.minus(1)].questionText
//    }






    fun onDeleteEventClick(event: com.example.sensimate.model2.Event) {
        launchCatching { storageService.deleteEvent(event.eventId) }
    }

//    fun onTitleChange(newValue: String) {
//        event.value = event.value.copy(title = newValue)
//    }
//
//    fun onDescriptionChange(newValue: String) {
//        event.value = event.value.copy(description = newValue)
//    }
//
//    fun onAddressChange(newValue: String) {
//        event.value = event.value.copy(address = newValue)
//    }
//
//    fun onPublicChange(newValue: Boolean) {
//        event.value = event.value.copy(public = newValue)
//    }
//
//    fun onDateChange(newValue: Long) {
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC))
//        calendar.timeInMillis = newValue
//        val newDueDate = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(calendar.time)
//        event.value = event.value.copy(date = newDueDate)
//    }
//
//    fun onTimeChange(hour: Int, minute: Int) {
//        val newDueTime = "${hour.toClockPattern()}:${minute.toClockPattern()}"
//        event.value = event.value.copy(time = newDueTime)
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