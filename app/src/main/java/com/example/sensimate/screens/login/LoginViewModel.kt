package com.example.sensimate.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.sensimate.core.Snackbar.SnackbarManager
import com.example.sensimate.core.isValidEmail
import com.example.sensimate.firebase_model.data.TempUserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.navigation.AuthScreen
import com.example.sensimate.navigation.Graph
import com.example.sensimate.screens.SensiMateViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.sensimate.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
) : SensiMateViewModel()  {
    var user = mutableStateOf(TempUserData())

        private set

    private val email
        get() = user.value.email
    private val password
        get() = user.value.password

    fun onEmailChange(newValue: String) {
        user.value = user.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        user.value = user.value.copy(password = newValue)
    }

    fun onLoginClick(navController: NavController) {

        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            navController.navigate(Graph.HOME)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }

    fun onToSignupClick(navController: NavController) {
        navController.navigate(AuthScreen.Signup.route)
    }
    suspend fun onStart(navController: NavController) {
        if (accountService.hasUser && !accountService.isAnonymous())
            navController.navigate(Graph.HOME)
        if (accountService.hasUser && accountService.isAnonymous())
            return
        else createID()
    }
    private suspend fun createID() {
        try {
            accountService.createAnonymousAccount()
        } catch (ex: FirebaseAuthException) {
            throw ex
        }
    }
}