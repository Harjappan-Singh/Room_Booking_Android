package com.example.roombooking.model

import com.example.roombooking.dao.StudentDao
import com.example.roombooking.database.AppDatabase


object StudentRepository {

    private lateinit var studentDao: StudentDao

    fun init(studentDao: StudentDao) {
        this.studentDao = studentDao
    }

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun getAllStudents(): List<Student> {
        return studentDao.getAllStudents()
    }
}