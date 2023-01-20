package com.example.sensimate.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.sensimate.core.isValidEmail
import com.example.sensimate.firebase_model.data.TempUserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.navigation.AuthScreen
import com.example.sensimate.navigation.Graph
import com.example.sensimate.screens.SensiMateViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Viewmodel for login page
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
) : SensiMateViewModel() {
    var user = mutableStateOf(TempUserData())
        private set


    private val email
        get() = user.value.email
    private val password
        get() = user.value.password

    // Updates the temp userdata with the value
    fun onEmailChange(newValue: String) {
        user.value = user.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        user.value = user.value.copy(password = newValue)
    }

    // Action of the login button
    fun onLoginClick(navController: NavController, context: Context) {

        if (!email.isValidEmail()) {
            Toast.makeText(context, "Der er fejl i emailen.", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isBlank()) {
            Toast.makeText(context, "Der er fejl i kodeordet.", Toast.LENGTH_SHORT).show()
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            storageService.getUserData(accountService.currentUserId)

            navController.navigate(Graph.HOME)
        }
    }

    // Action of the forgotten password button
    fun onForgotPasswordClick(context: Context) {
        if (!email.isValidEmail()) {
            Toast.makeText(context, "Der er fejl i emailen.", Toast.LENGTH_SHORT).show()
            return
        }
        launchCatching {
            accountService.sendRecoveryEmail(email) // Sends the recovery email to the users email address
        }
    }

    // Action for the sinup button
    fun onToSignupClick(navController: NavController) {
        // Checks if there is an user and whether it is anonymous or not
        launchCatching {
            // If there is an user and it is anonymous it gets deleted and a new user is created
            if (accountService.hasUser && accountService.isAnonymous()) {
                accountService.deleteAccount()
                createID()
                navController.navigate(AuthScreen.Signup.route)
            }
            // If there is an user and it is not anonymous it gets redirected, this should never occur
            if (accountService.hasUser && !accountService.isAnonymous()) {
                navController.navigate(AuthScreen.Signup.route)
            }
            // Else create an anonymous UserId and redirect
            else {
                createID()
                navController.navigate(AuthScreen.Signup.route)
            }


        }
    }

    // The onlaunch effect
    suspend fun onStart(navController: NavController) {
        // If there is an user and it is not anonymous it "logs in" automatically
        if (accountService.hasUser && !accountService.isAnonymous()) {
            storageService.getUserData(accountService.currentUserId)
            navController.popBackStack()
            navController.navigate(Graph.HOME)
        }
        // Else nothing happens
        else
            return

    }

    // The suspended function to create an userId
    private suspend fun createID() {
        try {
            accountService.createAnonymousAccount()
        } catch (ex: FirebaseAuthException) {
            throw ex
        }
    }
}