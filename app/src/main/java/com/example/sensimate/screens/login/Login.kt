package com.example.sensimate.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.basicButton
import com.example.sensimate.core.composables.EmailField
import com.example.sensimate.core.composables.PasswordField
import com.example.sensimate.core.fieldModifier
import com.example.sensimate.core.textButton
import com.example.sensimate.screens.edit_event.components.BasicToolbar
import com.example.sensimate.R.string as AppText

@Composable
fun Login(
    navController: NavController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
   val user by loginViewModel.user

    BasicToolbar(AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(user.email, loginViewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(user.password, loginViewModel::onPasswordChange, Modifier.fieldModifier())

        Button(
            onClick = {loginViewModel.onSignInClick(navController)},
            modifier = Modifier.basicButton()

        ) {
            Text(text = stringResource(AppText.sign_in))
        }
        TextButton(
            onClick = {loginViewModel.onForgotPasswordClick()},
            modifier = Modifier.textButton()
        ) {
            Text(text = stringResource(AppText.forgot_password))
        }
        Button(
            onClick = {loginViewModel.onSignupClick(navController)},
            modifier = Modifier.basicButton()

        ) {
            Text(text = stringResource(AppText.sign_up))
        }
    }

    LaunchedEffect(key1 = true) {
        loginViewModel.onStart(navController)
    }
}



