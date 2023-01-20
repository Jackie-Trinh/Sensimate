package com.example.sensimate.firebase_model.service.impl

import com.example.sensimate.firebase_model.data.User
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.trace
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// The service class that is used to access the authenthication in Fireabase
class AccountServiceImpl
@Inject
constructor(
    private val auth: FirebaseAuth
    ) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    // Authenticates the user, used for login
    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    // Sends a recover password mail from Firebase
    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    // Creates an anonymous account, is used to generate userId tokens
    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    // Links the email and password to the userId of current user, is used to create non anonymous users
    override suspend fun linkAccount(email: String, password: String): Unit =
        trace(LINK_ACCOUNT_TRACE) {
            val credential = EmailAuthProvider.getCredential(email, password)
            auth.currentUser!!.linkWithCredential(credential).await()
        }

    // Checks if the user is anonymous
    override suspend fun  isAnonymous(): Boolean {
        if (auth.currentUser!!.isAnonymous){
            return true
        }
        return false
    }

    // Deletes the user from Firebase Authentication
    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    // Signs out the user from the app by clearing the local cache
    override suspend fun signOut() {
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete() // If the user is anonymous it deletes them from Firebase
        }
        auth.signOut() // Clears the local cache of user
    }

    companion object {
        private const val LINK_ACCOUNT_TRACE = "linkAccount"
    }
}
