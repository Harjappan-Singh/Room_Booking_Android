package com.example.roombooking.model

data class Room(
    val id: String, // Room ID is room number (e.g., "P1105")
    val status: String // "available" or "booked"
)