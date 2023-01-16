package com.example.sensimate.data

data class EventItem(
    val id: Long,
    val name: String,
    val description: String,
    val date: String,
    val address: String
)
data class Event(
    val id: Long,
    val name: String,
    val description: String,
    val date: String,
    val address: String,
    val items: List<EventItem>
)

data class User(
    val id: Long,
    val email: String,
    val age: String,
    val sex: String,
    val postcode: String
)