package com.example.roombooking.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey val studentId: String,
    val name: String,
    val password: String
)