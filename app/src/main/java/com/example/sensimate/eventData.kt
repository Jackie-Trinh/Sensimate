package com.example.sensimate

data class EventItem(
    val id: Long,
    val name: String,
    val description: String
)
data class Event(
    val id: Long,
    val name: String,
    val description: String,
    val items: List<EventItem>
)