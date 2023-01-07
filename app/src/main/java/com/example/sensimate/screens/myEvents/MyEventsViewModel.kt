package com.example.sensimate.screens.myEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Survey
import com.example.sensimate.domain.repository.SurveyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val repo: SurveyRepository
) : ViewModel() {

    //Dialog
    var openDialog by mutableStateOf(false)

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }

    //Survey
    var survey by mutableStateOf(Survey(0, Constants.NO_VALUE, Constants.NO_VALUE))
        private set

    val surveys = repo.getSurveysFromRoom()

    fun getSurvey(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        survey = repo.getSurveyFromRoom(id)
    }

    fun addSurvey(survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repo.addSurveyToRoom(survey)
    }

    fun updateSurvey(survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateSurveyInRoom(survey)
    }

    fun deleteSurvey(survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteSurveyFromRoom(survey)
    }

    fun updateTitle(title: String) {
        survey = survey.copy(
            title = title
        )
    }

    fun updateAuthor(author: String) {
        survey = survey.copy(
            author = author
        )
    }

}