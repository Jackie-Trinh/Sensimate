package com.example.sensimate.screens.discover

import com.example.sensimate.core.Constants
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.service.StorageService
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




}

