package com.example.sensimate.screens.homeScreen

import androidx.compose.runtime.mutableStateOf
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.model2.UserData
import com.example.sensimate.model2.service.AccountService
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService,

    ) : SensiMateViewModel() {

    val userData = mutableStateOf(UserData())

    fun initialize() {

        val userId: String = accountService.currentUserId

        launchCatching {
            if (userId != "") {
                userData.value = storageService.getUserData(userId.idFromParameter()) ?: UserData()
            }
        }
    }

    fun onLogoutClick() {

        launchCatching {
            accountService.signOut()
        }

    }


}