package com.example.sensimate.screens.login

import com.example.sensimate.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.navigation.Graph

@Composable
fun Login(navController: NavController, loginViewModel: LoginViewModel) {

    val logo = painterResource(id = R.drawable.ic_sensimate)

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {

            Spacer(modifier = Modifier.padding(60.dp))

            Image(
                logo,
                contentDescription = "SensimateIcon",
                Modifier
                    .size(200.dp),
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                "Login",
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.padding(40.dp))

            Text(
                "BRUGERNAVN ELLER EMAIL",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            OutlinedTextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                label = { Text("Email Address") },
                placeholder = { Text(text = "Email Address") },
                singleLine = true
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                "KODEORD",
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(0.dp))

            OutlinedTextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                label = { Text("Password") },
                placeholder = { Text(text = "Password") },
                singleLine = true
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(50.dp)
            ) {
                Text(text = "Login", fontSize = 15.sp)
            }

        }

    }
}



