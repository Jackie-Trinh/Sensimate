package com.example.sensimate.screens.settings

import androidx.navigation.NavController
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

    // Gets the user ID of the current user
    private val userId
        get() = accountService.currentUserId


    // The function to delete a user
    fun onDeleteClick(navController: NavController) {
        launchCatching {
            storageService.deleteUserData(userId) // Deletes the user from the database
            accountService.deleteAccount() // Deletes the user from the authentication list
            navController.popBackStack() // Removes the backstack
            navController.navigate(Graph.AUTHENTICATION) // Goes back to login screen
        }
    }
}
