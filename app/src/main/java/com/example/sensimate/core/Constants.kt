package com.example.sensimate.core

class Constants {
    companion object {
        //Room
        const val EVENT_TABLE = "event_table"
        const val SURVEY_TABLE = "survey_table"
        const val QUESTION_TABLE = "question_table"
        const val USER_TABLE = "user_table"
        const val USER_ANSWERS_TABLE = "user_answers_table"

        //Screens
        const val EVENT_SCREEN = "Events"
        const val UPDATE_EVENT_SCREEN = "Update event"
        const val ADD_EVENT_SCREEN = "Add event"

        //Arguments
        const val EVENT_ID = "eventId"

        //Actions
        const val ADD_EVENT = "Add an event."
        const val DELETE_EVENT = "Delete an event."

        //Buttons
        const val ADD = "Add"
        const val DISMISS = "Dismiss"
        const val UPDATE = "Update"
        const val DELETE = "Delete"

        //Placeholders
        const val EVENT_TITLE = "Type the event title..."
        const val ADDRESS = "Type the address..."
        const val DATE = "Type the date..."
        const val DESCRIPTION = "Type the description..."
        const val NO_VALUE = ""
    }
}