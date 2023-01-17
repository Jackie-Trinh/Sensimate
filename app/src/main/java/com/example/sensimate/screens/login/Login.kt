package com.example.sensimate.screens.login

import androidx.compose.foundation.*
import com.example.sensimate.R
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.example.sensimate.navigation.Graph
import com.auth0.android.Auth0
import com.auth0.android.provider.WebAuthProvider
import com.example.sensimate.MainActivity
import com.example.sensimate.model.GradientButton

@Composable
fun Login(navController: NavController, loginViewModel: LoginViewModel) {

    loginViewModel.setContext(LocalContext.current)

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
                contentDescription = "SensimateIcon",
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
                "BRUGERNAVN ELLER EMAIL",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            TextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
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
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
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
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            }

            /*Button(
                onClick = {
                    loginViewModel.loginAuth0()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(50.dp)
            ) {
                Text(text = "Login", fontSize = 15.sp)
            }
            Button(
                onClick = {
                    if (loginViewModel.userIsAuthenticated) {
                        navController.popBackStack()
                        navController.navigate(Graph.HOME)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(50.dp)
            ) {
                Text(text = "Continue", fontSize = 15.sp)
            }*/

        }

    }
}



