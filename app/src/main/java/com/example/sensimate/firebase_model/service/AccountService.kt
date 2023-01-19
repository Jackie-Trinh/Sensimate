package com.example.sensimate.firebase_model.service

import com.example.sensimate.firebase_model.data.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun isAnonymous(): Boolean
    suspend fun deleteAccount()
    suspend fun signOut()
}
