package com.example.sensimate.firebase_model.service

interface LogService {
    fun logNonFatalCrash(Throwable: Throwable)
}