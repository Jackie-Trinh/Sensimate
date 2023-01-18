package com.example.sensimate.screens;

import com.example.sensimate.domain.repository.ImageRepository;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import android.net.Uri;
import androidx.compose.runtime.getValue;
import androidx.compose.runtime.mutableStateOf;
import androidx.compose.runtime.setValue;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.launch;
import com.example.sensimate.domain.model.Response.Loading;
import com.example.sensimate.domain.model.Response.Failure;
import com.example.sensimate.domain.model.Response.Success;
import com.example.sensimate.domain.repository.ImageRepository;

@HiltViewModel
class ImageViewModel @Inject constructor(
        private val repo: ImageRepository
    ):   {
        }