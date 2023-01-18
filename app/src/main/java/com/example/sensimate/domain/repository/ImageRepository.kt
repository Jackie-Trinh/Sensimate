package com.example.sensimate.domain.repository

import android.net.Uri
import com.example.sensimate.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun addImageToFirebaseStorage(imageUri: Uri): Response<Uri>

    suspend fun addImageToFirestore(downloadUri: Uri): Flow<Response<Boolean>>

    suspend fun getImageFromFirestore(): Flow<Response<String>>
    suspend fun addImageUrlToFirestore(downloadUrl: Uri): Response<Boolean>
    suspend fun getImageUrlFromFirestore(): Response<String>
}