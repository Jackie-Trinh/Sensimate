package com.example.sensimate.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.basicButton
import com.example.sensimate.core.composables.*
import com.example.sensimate.core.fieldModifier
import com.example.sensimate.screens.edit_event.components.BasicToolbar
import com.example.sensimate.R.string as AppText

@Composable
fun Signup (
    navController: NavController,
    modifier: Modifier = Modifier,
    signupViewModel: SignupViewModel = hiltViewModel()
    ) {
    val user by signupViewModel.user

    BasicToolbar(AppText.create_account)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UsernameField(user.username, signupViewModel::onUsernameChange, Modifier.fieldModifier())
        EmailField(user.email, signupViewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(user.password, signupViewModel::onPasswordChange, Modifier.fieldModifier())
        RepeatPasswordField(user.repeatPassword, signupViewModel::onRepeatPasswordChange, Modifier.fieldModifier())
        AgeField(user.age, signupViewModel::onAgeChange, Modifier.fieldModifier())
        PostalField(user.postal, signupViewModel::onPostalChange, Modifier.fieldModifier())

        Button(
            onClick = {signupViewModel.onSignupClick(navController)},
            modifier = Modifier.basicButton()

        ) {
            Text(text = stringResource(AppText.sign_up))
        }
    }
}