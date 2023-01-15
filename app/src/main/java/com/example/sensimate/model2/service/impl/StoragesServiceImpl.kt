package com.example.sensimate.model2.service.impl


import com.example.sensimate.model2.Event
import com.example.sensimate.model2.service.AccountService
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.model2.service.trace
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.asDeferred

class StorageServiceImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
    ) :
    StorageService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val events: Flow<List<Event>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                currentCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects() }
            }

    override suspend fun getEvent(eventId: String): Event? =
        currentCollection(auth.currentUserId).document(eventId).get().await().toObject()

    override suspend fun save(event: Event): String =
        trace(SAVE_EVENT_TRACE) { currentCollection(auth.currentUserId).add(event).await().id }

    override suspend fun update(event: Event): Unit =
        trace(UPDATE_EVENT_TRACE) {
            currentCollection(auth.currentUserId).document(event.id).set(event).await()
        }

    override suspend fun delete(eventId: String) {
        currentCollection(auth.currentUserId).document(eventId).delete().await()
    }

    // TODO: It's not recommended to delete on the client:
    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
    override suspend fun deleteAllForUser(userId: String) {
        val matchingEvents = currentCollection(userId).get().await()
        matchingEvents.map { it.reference.delete().asDeferred() }.awaitAll()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.document(uid).collection(EVENT_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val EVENT_COLLECTION = "events"
        private const val SAVE_EVENT_TRACE = "saveEvent"
        private const val UPDATE_EVENT_TRACE = "updateEvent"
    }
}
