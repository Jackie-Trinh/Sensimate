package com.example.sensimate.model2.service.impl


import com.example.sensimate.model2.Event
import com.example.sensimate.model2.Question
import com.example.sensimate.model2.UserData
import com.example.sensimate.model2.service.AccountService
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.model2.service.trace
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow

class StorageServiceImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService,
    ) :
    StorageService {

    companion object {
        private const val USER_COLLECTION = "users"

        private const val USERDATA_COLLECTION = "userDatas"
        private const val SAVE_USERDATA_TRACE = "saveUserData"
        private const val UPDATE_USERDATA_TRACE = "updateUserData"


        private const val EVENT_COLLECTION = "events"
        private const val SAVE_EVENT_TRACE = "saveEvent"
        private const val UPDATE_EVENT_TRACE = "updateEvent"

        private const val QUESTION_COLLECTION = "questions"
        private const val SAVE_QUESTION_TRACE = "saveQuestion"
        private const val UPDATE_QUESTION_TRACE = "updateQuestion"
    }
    //<-- USERDATA -->
//    @OptIn(ExperimentalCoroutinesApi::class)
//    override val userDatas: Flow<List<UserData>>
//        get() =
//            auth.currentUser.flatMapLatest { user ->
//                currentUserDataCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects() }
//            }
//
    override suspend fun getUserData(userDataId: String): UserData? =
        currentUserDataCollection(auth.currentUserId).document(userDataId).get().await().toObject()

    override suspend fun saveUserData(userData: UserData): String =
        trace(SAVE_USERDATA_TRACE) { currentUserDataCollection(auth.currentUserId).add(userData).await().id }

    override suspend fun updateUserData(userData: UserData): Unit =
        trace(UPDATE_USERDATA_TRACE) {
            currentUserDataCollection(auth.currentUserId).document(userData.userId).set(userData).await()
        }
//
    override suspend fun deleteUserData(userDataId: String) {
        currentUserDataCollection(auth.currentUserId).document(userDataId).delete().await()
    }

//    // TODO: It's not recommended to delete on the client:
//    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
//    override suspend fun deleteAllForUserData(userDataId: String) {
//        val matchingEvents = currentCollection(userDataId).get().await()
//        matchingEvents.map { it.reference.delete().asDeferred() }.awaitAll()
//    }

    private fun currentUserDataCollection(uid: String): CollectionReference =
        firestore.document(uid).collection(USERDATA_COLLECTION)


    //<-- Event -->
    @OptIn(ExperimentalCoroutinesApi::class)
    override val events: Flow<List<Event>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                currentEventCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects() }
            }

    override suspend fun getEvent(eventId: String): Event? =
        currentEventCollection(auth.currentUserId).document(eventId).get().await().toObject()

    override suspend fun saveEvent(event: Event): String =
        trace(SAVE_EVENT_TRACE) { currentEventCollection(auth.currentUserId).add(event).await().id }

    override suspend fun updateEvent(event: Event): Unit =
        trace(UPDATE_EVENT_TRACE) {
            currentEventCollection(auth.currentUserId).document(event.eventId).set(event).await()
        }

    override suspend fun deleteEvent(eventId: String) {
        currentEventCollection(auth.currentUserId).document(eventId).delete().await()
    }

//    // TODO: It's not recommended to delete on the client:
//    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
//    override suspend fun deleteAllForUser(userId: String) {
//        val matchingEvents = currentCollection(userId).get().await()
//        matchingEvents.map { it.reference.delete().asDeferred() }.awaitAll()
//    }

    private fun currentEventCollection(uid: String): CollectionReference =
        firestore.document(uid).collection(EVENT_COLLECTION)


    //<-- Question -->
//    override suspend fun getQuestionsForEvent(eventId: String): List<Question?> =
//        currentQuestionCollection(auth.currentUserId, eventId).whereEqualTo("eventId", eventId).get().await().toObjects()

    override suspend fun getQuestionsForEvent(eventId: String): ArrayList<Question> {
        val questions = ArrayList<Question>()

        currentQuestionCollection(auth.currentUserId, eventId).get().addOnSuccessListener { snapshot ->
                for (document in snapshot.documents) {
                    document.toObject<Question>()?.let { questions.add(it) }
                }
        }
        return questions
    }
//    override suspend fun getQuestionsForEvent(eventId: String): List<Question?> {
//        currentQuestionCollection(auth.currentUserId, eventId).get().addOnSuccessListener { snapshot ->
//            for (document in snapshot.documents) {
//
//                return List<Question?> questions = document
//
//                    return listOf(Question(document))
//            }
//        }
//    }


    override suspend fun getQuestion(eventId: String, questionId: String): Question? =
        currentQuestionCollection(auth.currentUserId, eventId).document(questionId).get().await().toObject()

    override suspend fun saveQuestion(eventId: String, question: Question): String =
        trace(SAVE_QUESTION_TRACE) { currentQuestionCollection(auth.currentUserId, eventId).add(question).await().id }

    override suspend fun updateQuestion(eventId: String, question: Question): Unit =
        trace(UPDATE_QUESTION_TRACE) {
            currentQuestionCollection(auth.currentUserId, eventId).document(question.questionId).set(question).await()
        }

    override suspend fun deleteQuestion(eventId: String, questionId: String) {
        currentQuestionCollection(auth.currentUserId, eventId).document(questionId).delete().await()
    }

    private fun currentQuestionCollection(uid: String, eventId: String): CollectionReference =
        firestore.document(uid).collection(EVENT_COLLECTION).document(eventId).collection(QUESTION_COLLECTION)

}
