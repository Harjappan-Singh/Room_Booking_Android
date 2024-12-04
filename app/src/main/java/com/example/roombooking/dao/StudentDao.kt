package com.example.roombooking.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roombooking.model.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Query("SELECT * FROM student_table WHERE studentId = :id AND password = :password")
    suspend fun getStudent(id: String, password: String): Student?

    @Query("SELECT * FROM student_table")
    suspend fun getAllStudents(): List<Student>
}
