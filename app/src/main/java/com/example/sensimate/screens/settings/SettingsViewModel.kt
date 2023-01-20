package com.example.sensimate.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.navigation.Graph
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
) : SensiMateViewModel()  {

    var userData = mutableStateOf(UserData())
        private set

    private val userId
        get() = accountService.currentUserId


    fun onDeleteClick(navController: NavController) {
        launchCatching {
            storageService.deleteUserData(userId)
            accountService.deleteAccount()
            navController.popBackStack()
            navController.navigate(Graph.AUTHENTICATION)
        }
    }


}
