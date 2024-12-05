package com.example.roombooking.model

data class Room(
    val room_id: String, // Room ID (e.g., "P1105")
    val status: String, // "Booked" or "Available"
    val student_id: String? // Nullable for available rooms
)
