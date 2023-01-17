package com.example.sensimate.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.foundation.*
import com.example.sensimate.R
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.navigation.Graph
import com.example.sensimate.MainActivity
import com.example.sensimate.model.GradientButton
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
    val logo = painterResource(id = R.drawable.ic_sensimate)

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current //clear focus


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        contentAlignment = Alignment.TopCenter
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp)
        ) {

            Spacer(modifier = Modifier.padding(20.dp))

            Image(
                logo,
                contentDescription = "SensiMateIcon",
                Modifier
                    .size(100.dp),
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                "Login",
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.padding(30.dp))

            Text(
                "EMAIL",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.email,
                onValueChange = { loginViewModel::onEmailChange },
                modifier = Modifier
                    .border(width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(percent = 100)),
                placeholder = { Text(text = "Email Address") },
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor =  Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "KODEORD",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.password,
                onValueChange = { loginViewModel::onPasswordChange },
                modifier = Modifier.border(width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(percent = 100))
                    ,
                placeholder = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                focusedIndicatorColor =  Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            GradientButton(navController = navController, text = "Login", state = true) {
                loginViewModel.onSignInClick(navController)
            }
            TextButton(
                onClick = {loginViewModel.onForgotPasswordClick()},
                modifier = Modifier.textButton()
            ) {
                Text(text = stringResource(AppText.forgot_password))
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = {loginViewModel.onSignupClick(navController)},
                modifier = Modifier.basicButton()
            ) {
                Text(text = stringResource(AppText.sign_up))
            }

    }
    LaunchedEffect(key1 = true) {
        loginViewModel.onStart(navController)