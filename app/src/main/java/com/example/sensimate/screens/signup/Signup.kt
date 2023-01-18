package com.example.sensimate.screens.signup

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.core.basicButton
import com.example.sensimate.core.composables.*
import com.example.sensimate.core.fieldModifier
import com.example.sensimate.core.textButton
import com.example.sensimate.model.GradientButton
import com.example.sensimate.R.string as AppText

@Composable
fun Signup (
    navController: NavController,
    modifier: Modifier = Modifier,
    signupViewModel: SignupViewModel = hiltViewModel()
    ) {
    val user by signupViewModel.user

    val logo = painterResource(id = R.drawable.ic_sensimate)

    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    val check18 = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
                .verticalScroll(scrollState, true)
                .padding(10.dp)
        ) {


            Image(
                logo,
                contentDescription = "SensiMateIcon",
                Modifier
                    .size(100.dp),
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "BRUGERNAVN",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.username,
                onValueChange = { signupViewModel.onUsernameChange(it) },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(percent = 100)
                    ),
                placeholder = { Text(text = "Brugernavn") },
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "EMAIL",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.email,
                onValueChange = { signupViewModel.onEmailChange(it) },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(percent = 100)
                    ),
                placeholder = { Text(text = "Email Address") },
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
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
                onValueChange = { signupViewModel.onPasswordChange(it) },
                modifier = Modifier.border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(percent = 100)
                ),
                placeholder = { Text(text = "Kodeord") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "BEKRÆFT KODEORD",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.repeatPassword,
                onValueChange = { signupViewModel.onRepeatPasswordChange(it)},
                modifier = Modifier.border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(percent = 100)
                ),
                placeholder = { Text(text = "Bekræft Kodeord") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "FØDSELDATO",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.age,
                onValueChange = { signupViewModel.onAgeChange(it) },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(percent = 100)
                    ),
                placeholder = { Text(text = "Fødseldato") },
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "POSTNUMMER",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = user.postal,
                onValueChange = { signupViewModel.onPostalChange(it) },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(percent = 100)
                    ),
                placeholder = { Text(text = "Postnummer") },
                singleLine = true,
                shape = RoundedCornerShape(percent = 100),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            /*Checkbox(
                checked = check18.value,
                onCheckedChange = { check18.value = it }
            )*/

            Spacer(modifier = Modifier.padding(5.dp))

            GradientButton(navController = navController, text = "Tilmeld", state = true) {
                if (check18.value)
                    signupViewModel.onSignupClick(navController)
            }
        }
    }
}