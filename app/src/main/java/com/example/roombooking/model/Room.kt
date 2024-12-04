package com.example.roombooking.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Room(
//    val id: String, // Room ID is room number (e.g., "P1105")
//    val status: String // "available" or "booked"
//)

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "rooms")
data class Room(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,   // Room ID is room number (e.g., "P1105")
    val status: String, // "available" or "booked"
    val date: String    //  (e.g., "01/12/2024")
)