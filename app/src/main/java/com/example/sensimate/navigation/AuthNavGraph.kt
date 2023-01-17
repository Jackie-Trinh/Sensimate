package com.example.sensimate.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sensimate.screens.login.Login
import com.example.sensimate.screens.login.LoginViewModel

//The authentication navigation graph, for login, sign up etc. with no bottom bar
fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        //Login screen
        composable(AuthScreen.Login.route) {
            Login(navController = navController, loginViewModel = LoginViewModel())
        }
        //Signup screen
            //composable(AuthScreen.SignUp.route) {
            //    SignUp(navController = navController, signUpViewModel = SignUpViewModel())
            //}
        //Forgot screen
            //composable(AuthScreen.Forgot.route) {
            //    Forgot(navController = navController, forgotViewModel = ForgotViewModel())
            //}
    }
}

//make the routes for the authentication screen
sealed class AuthScreen(val route: String){
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}