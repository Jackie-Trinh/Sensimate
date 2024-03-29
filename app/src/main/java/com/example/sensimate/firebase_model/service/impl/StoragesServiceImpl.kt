package com.example.sensimate.firebase_model.service.impl


import android.content.ContentValues.TAG
import android.util.Log
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.Question
import com.example.sensimate.firebase_model.data.UserAnswer
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.firebase_model.service.AccountService
import com.example.sensimate.firebase_model.service.StorageService
import com.example.sensimate.firebase_model.service.trace
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

        private const val USER_ANSWER_COLLECTION = "questions"
        private const val SAVE_USER_ANSWER_TRACE = "saveQuestion"
        private const val UPDATE_USER_ANSWER_TRACE = "updateQuestion"
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
        currentUserDataCollection().document(userDataId).get().await().toObject()

//    override suspend fun saveUserData(userData: UserData): String =
//        trace(SAVE_USERDATA_TRACE) { currentUserDataCollection().add(userData).await().id }

    override suspend fun saveUserData(userData: UserData) {
        currentUserDataCollection().document(userData.userId)
            .set(userData)
    }

    override suspend fun updateUserData(userData: UserData): Unit =
        trace(UPDATE_USERDATA_TRACE) {
            currentUserDataCollection().document(userData.userId).set(userData).await()
        }

    //
    override suspend fun deleteUserData(userDataId: String) {
        currentUserDataCollection().document(userDataId).delete().await()
    }

    private fun currentUserDataCollection(): CollectionReference =
        firestore.collection(USERDATA_COLLECTION)

//    // TODO: It's not recommended to delete on the client:
//    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
//    override suspend fun deleteAllForUserData(userDataId: String) {
//        val matchingEvents = currentCollection(userDataId).get().await()
//        matchingEvents.map { it.reference.delete().asDeferred() }.awaitAll()
//    }


    //<-- Event -->
    @OptIn(ExperimentalCoroutinesApi::class)
    override val events: Flow<List<Event>>
        get() = currentEventCollection().snapshots().map { snapshot -> snapshot.toObjects() }


    override suspend fun getEvent(eventId: String): Event? =
        currentEventCollection().document(eventId).get().await().toObject()


    override suspend fun getEventsForUser(userData: UserData): ArrayList<Event> {
        val events = ArrayList<Event>()

        for (eventId in userData.followedEventIds) { // first make a copy of the list
            currentEventCollection().document(eventId)
                .get().await().toObject<Event>()?.let {
                    events.add(it)
                }
        }

        return events
    }

    override suspend fun saveEvent(event: Event): String =
        trace(SAVE_EVENT_TRACE) { currentEventCollection().add(event).await().id }

    override suspend fun updateEvent(event: Event): Unit =
        trace(UPDATE_EVENT_TRACE) {
            currentEventCollection().document(event.eventId).set(event).await()
        }

    override suspend fun deleteEvent(eventId: String) {
        currentEventCollection().document(eventId).delete().await()
    }


    private fun currentEventCollection(): CollectionReference =
        firestore.collection(EVENT_COLLECTION)


    //<-- Question -->
//    override suspend fun getQuestionsForEvent(eventId: String): ArrayList<Question> {
//        val questions = ArrayList<Question>()
//
//        currentQuestionCollection(eventId).get().addOnSuccessListener { snapshot ->
//                for (document in snapshot.documents) {
//                    document.toObject<Question>()?.let { questions.add(it) }
//                }
//        }
//        return questions
//    }

    override suspend fun getQuestionsForEvent(eventId: String): ArrayList<Question> {
        val questions = ArrayList<Question>()

        currentQuestionCollection(eventId)
            .get().await().documents.forEach { document ->
                document.toObject<Question>().let {
                    if (it != null) {
                        questions.add(it)
                    }
                }
            }

        return questions
    }


//    override suspend fun getQuestionsForEvent(eventId: String): Flow<List<Question>> {
//        return currentQuestionCollection(eventId = eventId).snapshots().map { querySnapshot -> querySnapshot.toObjects()}
//    }


    override suspend fun getQuestion(eventId: String, questionId: String): Question? =
        currentQuestionCollection(eventId).document(questionId).get().await().toObject()

    override suspend fun saveQuestion(eventId: String, question: Question): String =
        trace(SAVE_QUESTION_TRACE) { currentQuestionCollection(eventId).add(question).await().id }

    override suspend fun updateQuestion(eventId: String, question: Question): Unit =
        trace(UPDATE_QUESTION_TRACE) {
            currentQuestionCollection(eventId).document(question.questionId).set(question).await()
        }

    override suspend fun deleteQuestion(eventId: String, questionId: String) {
        currentQuestionCollection(eventId).document(questionId).delete().await()
    }

    // TODO: It's not recommended to delete on the client:
    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
    override suspend fun deleteAllQuestionsForEvent(eventId: String) {
        val matchingEvents = currentQuestionCollection(eventId).get().await()
        matchingEvents.map { it.reference.delete().asDeferred() }.awaitAll()
    }

    private fun currentQuestionCollection(eventId: String): CollectionReference =
        firestore.collection(EVENT_COLLECTION).document(eventId).collection(QUESTION_COLLECTION)





    //<-- UserAnswer -->
    override suspend fun getUserAnswerForEvent(eventId: String): ArrayList<UserAnswer> {
        val userAnswers = ArrayList<UserAnswer>()

        currentUserAnswerCollection(eventId)
            .get().await().documents.forEach { document ->
                document.toObject<UserAnswer>().let {
                    if (it != null) {
                        userAnswers.add(it)
                    }
                }
            }

        return userAnswers
    }
    override suspend fun getUserAnswer(eventId: String, userAnswerId: String): UserAnswer? =
        currentUserAnswerCollection(eventId).document(userAnswerId).get().await().toObject()

    override suspend fun saveUserAnswer(eventId: String, userAnswer: UserAnswer): String =
        trace(SAVE_USER_ANSWER_TRACE) { currentUserAnswerCollection(eventId).add(userAnswer).await().id }

    override suspend fun updateUserAnswer(eventId: String, userAnswer: UserAnswer): Unit =
        trace(UPDATE_USER_ANSWER_TRACE) {
            currentUserAnswerCollection(eventId).document(userAnswer.userAnswerId).set(userAnswer).await()
        }

    override suspend fun deleteUserAnswer(eventId: String, userAnswerId: String) {
        currentUserAnswerCollection(eventId).document(userAnswerId).delete().await()
    }

    // TODO: It's not recommended to delete on the client:
    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
    override suspend fun deleteAllUserAnswerForEvent(eventId: String) {
        val matchingEvents = currentUserAnswerCollection(eventId).get().await()
        matchingEvents.map { it.reference.delete().asDeferred() }.awaitAll()
    }

    private fun currentUserAnswerCollection(eventId: String): CollectionReference =
        firestore.collection(EVENT_COLLECTION).document(eventId).collection(USER_ANSWER_COLLECTION)

}
