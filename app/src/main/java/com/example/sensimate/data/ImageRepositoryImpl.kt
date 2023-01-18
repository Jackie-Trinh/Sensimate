package com.example.sensimate.data

import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import com.example.sensimate.core.const.CREATED_AT
import com.example.sensimate.core.const.IMAGES
import com.example.sensimate.core.const.IMAGE_NAME
import com.example.sensimate.core.const.UID
import com.example.sensimate.domain.model.Response
import com.example.sensimate.domain.repository.ImageRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import com.example.sensimate.domain.model.Response.Failure
import com.example.sensimate.domain.model.Response.Success

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
) : ImageRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri): Response<Uri> {
        return try {
            val downloadUrl = storage.reference.child(IMAGES).child(IMAGE_NAME)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Success(downloadUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }


    override suspend fun addImageUrlToFirestore(downloadUrl: Uri): Response<Boolean> {
        return try {
            db.collection(IMAGES).document(UID).set(mapOf(
                URL to downloadUrl,
                CREATED_AT to FieldValue.serverTimestamp()
            )).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getImageUrlFromFirestore(): Response<String> {
        return try {
            val imageUrl = db.collection(IMAGES).document(UID).get().await().getString(URL)
            Success(imageUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
