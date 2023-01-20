package com.example.sensimate.screens.discover

import androidx.compose.runtime.mutableStateOf
import com.example.sensimate.core.Constants
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.SensiMateViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val storageService: StorageService,
//    private val configurationService: ConfigurationService,
    private val accountService: AccountService,
) : SensiMateViewModel() {


    val events = storageService.events
//    var userData = mutableStateOf(UserData())
//
//    fun initialize() {
//        launchCatching {
//
//            userData.value = storageService.getUserData(accountService.currentUserId)!!
//
//        }
//    }



    //open clicked event
    fun onEventClick(openScreen: (String) -> Unit, event: Event)
            = openScreen("${BottomBarScreen.EventPage2.route}?${Constants.EVENT_ID}={${event.eventId}}")





}

