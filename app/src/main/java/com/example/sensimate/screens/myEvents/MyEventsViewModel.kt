package com.example.sensimate.screens.myEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.EVENT_ID
import com.example.sensimate.domain.repository.EventRepository
import com.example.sensimate.model2.Event
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val storageService: StorageService,
//    private val configurationService: ConfigurationService,
) : SensiMateViewModel() {
    val options = mutableStateOf<List<String>>(listOf())

    val events = storageService.events

    fun loadEventOptions() {
//        val hasEditOption = configurationService.isShowEventEditButtonConfig
        options.value = EventActionOption.getOptions(/*hasEditOption*/)
    }

//    fun onEventCheckChange(event: Event) {
//        launchCatching { storageService.update(event.copy(completed = !event.completed)) }
//    }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(BottomBarScreen.EditEvent.route)

//    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onEventActionClick(openScreen: (String) -> Unit, event: Event, action: String) {
        when (EventActionOption.getByTitle(action)) {
            EventActionOption.EditEvent -> openScreen("${BottomBarScreen.EditEvent.route}?$EVENT_ID={${event.id}}")
            EventActionOption.DeleteEvent -> onDeleteEventClick(event)
        }
    }

//    private fun onFlagEventClick(event: Event) {
//        launchCatching { storageService.update(event.copy(flag = !event.flag)) }
//    }
//
    private fun onDeleteEventClick(event: Event) {
        launchCatching { storageService.delete(event.id) }
    }
}

