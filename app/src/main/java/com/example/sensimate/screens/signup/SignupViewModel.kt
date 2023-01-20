package com.example.sensimate.screens.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.sensimate.core.*
import com.example.sensimate.firebase_model.data.TempUserData
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.navigation.Graph
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


// Viewmodel for signup page
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
) : SensiMateViewModel() {
    var user = mutableStateOf(TempUserData())
        private set


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


    // Changes the values in the temp data
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

    // The action of the signup button
    fun onSignupClick(navController: NavController, context: Context) {
        if (!email.isValidEmail()) {
            Toast.makeText(context, "Der er fejl i emailen.", Toast.LENGTH_SHORT).show()
            return
        }
        if (!password.isValidPassword()) {
            Toast.makeText(context, "Kodeordet skal indholde minimum 8 tegn,\n" +
                    "et stort bogstav, et lille bogstav og et tal.", Toast.LENGTH_SHORT).show()
            return
        }
        if (!password.passwordMatches(user.value.repeatPassword)) {
            Toast.makeText(context, "Kodeorderne matcher ikke.", Toast.LENGTH_SHORT).show()
            return
        }
        if (!age.isValidAge()) {
            Toast.makeText(context, "Skriv din fødselsdato 'dd-mm-yyyy'", Toast.LENGTH_SHORT).show()
            return
        }
        if (!sex.isValidSex()) {
            Toast.makeText(context, "Skriv dit køn 'mand eller kvinde'", Toast.LENGTH_SHORT).show()
            return
        }
        if (!postal.isValidPostal()) {
            Toast.makeText(context, "Skriv dit postnummer 'xxxx'", Toast.LENGTH_SHORT).show()
            return
        }
        launchCatching {
            accountService.linkAccount(email, password)

            val userData = UserData(
                userId = accountService.currentUserId,
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