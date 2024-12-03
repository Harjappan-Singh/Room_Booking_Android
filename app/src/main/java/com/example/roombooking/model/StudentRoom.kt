package com.example.roombooking.model

data class StudentRoom(
    val studentID: String, // Student ID is student number (e.g., "d00262135")
    val roomID: String, // Room ID is room number (e.g., "P1105")
    val dateBooked: String // Date booked (e.g., "01/12/2024")
)
