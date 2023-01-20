package com.example.sensimate.screens.event_page

import androidx.compose.runtime.mutableStateOf
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.EVENT_ID
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.SensiMateViewModel
import com.example.sensimate.screens.survey.QuestionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventPageViewModel @Inject constructor(
//    logService: LogService,
    private val storageService: StorageService,
    private val accountService: AccountService,
) : SensiMateViewModel() {
    val event = mutableStateOf(Event())

    var userData = mutableStateOf(UserData())


    fun initialize(eventId: String) {
        launchCatching {
            userData.value = storageService.getUserData(accountService.currentUserId)!!
            if (eventId != Constants.EVENT_DEFAULT_ID) {
                event.value = storageService.getEvent(eventId.idFromParameter()) ?: Event()
            }
        }
    }

    fun onFollowEventClick() {
        launchCatching {
            val followedTemp = userData.value.followedEventIds as MutableList<String>
            followedTemp.add(event.value.eventId)

            userData.value = userData.value.copy(followedEventIds = followedTemp)

            storageService.saveUserData(userData = userData.value)
        }
    }

    fun onBackClick(popUpScreen: () -> Unit) {
        launchCatching {
            popUpScreen()
        }
    }

    fun onOpenSurveyClick(openScreen: (String) -> Unit, event: Event) =
        openScreen("${BottomBarScreen.SurveyScreen.route}?$EVENT_ID={${event.eventId}}")


}