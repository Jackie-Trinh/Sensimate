package com.example.sensimate.screens.event_page

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.EVENT_ID
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.model2.Event
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventPageViewModel @Inject constructor(
//    logService: LogService,
    private val storageService: StorageService,
) : SensiMateViewModel() {
    val event = mutableStateOf(Event())

    fun initialize(eventId: String) {
        launchCatching {
            if (eventId != Constants.EVENT_DEFAULT_ID) {
                event.value = storageService.getEvent(eventId.idFromParameter()) ?: Event()
            }
        }
    }

    fun onBackClick(popUpScreen: () -> Unit) {
        launchCatching {
            popUpScreen()
        }
    }

    fun onOpenSurveyClick(openScreen: (String) -> Unit, event: Event) =
        openScreen("${BottomBarScreen.Survey2.route}?$EVENT_ID={${event.eventId}}")
}