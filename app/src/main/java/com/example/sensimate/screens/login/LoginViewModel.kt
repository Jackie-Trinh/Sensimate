package com.example.sensimate.screens.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.sensimate.R
import com.example.sensimate.data.Userdata
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.navigation.Graph

class LoginViewModel : ViewModel() {

    var appJustLaunched by mutableStateOf(true)

    var userIsAuthenticated by mutableStateOf(false)

    var user by mutableStateOf(Userdata())

    private val TAG = "LoginViewModel"

    private lateinit var account: Auth0

    private lateinit var context: Context

    fun setContext(activityContext: Context) {

        context = activityContext

        account = Auth0(
            context.getString(R.string.com_auth0_client_id),
            context.getString(R.string.com_auth0_domain)
        )
    }

    fun login() {

        WebAuthProvider
            .login(account)
            .withScheme(context.getString(R.string.com_auth0_scheme))
            .start(context, object : Callback<Credentials, AuthenticationException> {

                override fun onFailure(error: AuthenticationException) {
                    // The user either pressed the “Cancel” button
                    // on the Universal Login screen or something
                    // unusual happened.
                    Log.d(TAG, "Error occured in login(): ${error.toString()} ")
                }

                override fun onSuccess(result: Credentials) {
                    // The user successfully logged in.
                    val idToken = result.idToken
                    Log.d(TAG, "ID token: $idToken")
                    user = Userdata(idToken)
                    userIsAuthenticated = true
                    appJustLaunched = false
                }
            })
    }

    fun logout() {

        WebAuthProvider
            .logout(account)
            .withScheme(context.getString(R.string.com_auth0_scheme))
            .start(context, object : Callback<Void?, AuthenticationException> {

                override fun onFailure(error: AuthenticationException) {
                    // For some reason, logout failed.
                    Log.d(TAG, "Error occured in login(): ${error.toString()} ")
                }

                override fun onSuccess(result: Void?) {
                    // The user successfully logged out.
                    user = Userdata()
                    userIsAuthenticated = false
                }
            })
    }
}