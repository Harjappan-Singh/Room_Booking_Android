package com.example.roombooking.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentNumber: String, // Student Number is student number (e.g., "d00262135")
    val name: String, // "Yee Chean Chang" or "Harjappan"
    val password: String // Store password (e.g., "password123")
)
