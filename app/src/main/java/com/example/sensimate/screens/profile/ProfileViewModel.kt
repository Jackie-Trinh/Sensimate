package com.example.sensimate.screens.profile

import androidx.compose.runtime.mutableStateOf
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService,

) : SensiMateViewModel() {

    var userData = mutableStateOf(UserData())
        private set

    private val username
        get() = userData.value.username
    private val email
        get() = userData.value.email
    private val age
        get() = userData.value.age
    private val postal
        get() = userData.value.postal
    private val sex
        get() = userData.value.sex

    fun initialize() {
        launchCatching {
            userData.value = userData.value.copy(
                username = storageService.getUserData(accountService.currentUserId)!!.username
            )
            userData.value = userData.value.copy(
                email = storageService.getUserData(accountService.currentUserId)!!.email
            )
            userData.value = userData.value.copy(
                age = storageService.getUserData(accountService.currentUserId)!!.age
            )
            userData.value = userData.value.copy(
                postal = storageService.getUserData(accountService.currentUserId)!!.postal
            )
            userData.value = userData.value.copy(
                sex = storageService.getUserData(accountService.currentUserId)!!.sex
            )
        }
    }
}