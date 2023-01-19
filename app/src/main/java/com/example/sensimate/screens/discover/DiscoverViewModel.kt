package com.example.sensimate.screens.discover

import com.example.sensimate.core.Constants
import com.example.sensimate.model2.Event
import com.example.sensimate.model2.UserData
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.SensiMateViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val storageService: StorageService,
//    private val configurationService: ConfigurationService,
) : SensiMateViewModel() {


    val events = storageService.events

    //open clicked event
    fun onEventClick(openScreen: (String) -> Unit, event: Event)
            = openScreen("${BottomBarScreen.EventPage2.route}?${Constants.EVENT_ID}={${event.eventId}}")

    //open clicked event
    fun onAddUserClick(userData: UserData) {
        launchCatching {
            storageService.saveUserData(userData)
        }
    }


}

