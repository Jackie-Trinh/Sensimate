package com.example.sensimate.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.sensimate.R.string as AppText
import com.example.sensimate.core.Snackbar.SnackbarManager
import com.example.sensimate.core.isValidEmail
import com.example.sensimate.core.isValidPassword
import com.example.sensimate.core.isValidUserName
import com.example.sensimate.core.passwordMatches
import com.example.sensimate.data.Userdata
import com.example.sensimate.model2.UserData

import com.example.sensimate.model2.service.AccountService
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.navigation.Graph
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
) : SensiMateViewModel() {
    var user = mutableStateOf(Userdata())
        private set

    private val username
        get() = user.value.username
    private val email
        get() = user.value.email
    private val password
        get() = user.value.password
    private val age
        get() = user.value.age
    private val postal
        get() = user.value.postal
    private val sex
        get() = user.value.sex


    fun onUsernameChange(newValue: String) {
        user.value = user.value.copy(username = newValue)
    }
    fun onEmailChange(newValue: String) {
        user.value = user.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        user.value = user.value.copy(password = newValue)
    }
    fun onRepeatPasswordChange(newValue: String) {
        user.value = user.value.copy(repeatPassword = newValue)
    }
    fun onAgeChange(newValue: String) {
        user.value = user.value.copy(age = newValue)
    }
    fun onSexChange(newValue: String) {
        user.value = user.value.copy(sex = newValue)
    }
    fun onPostalChange(newValue: String) {
        user.value = user.value.copy(postal = newValue)
    }

    fun onSignupClick(navController: NavController) {
        if (!username.isValidUserName()) {
            SnackbarManager.showMessage(AppText.username_error)
            return
        }
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }
        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }
        if (!password.passwordMatches(user.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }
        launchCatching {
            accountService.linkAccount(email, password)

            val userData = UserData(
                userId = accountService.currentUserId,
                username = username,
                email = email,
                age = age,
                sex = sex,
                postal = postal
            )
            storageService.saveUserData(userData)

            navController.popBackStack()
            navController.navigate(Graph.HOME)
        }
    }
}