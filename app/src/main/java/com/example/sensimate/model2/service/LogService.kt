package com.example.sensimate.model2.service

interface LogService {
    fun logNonFatalCrash(Throwable: Throwable)
}