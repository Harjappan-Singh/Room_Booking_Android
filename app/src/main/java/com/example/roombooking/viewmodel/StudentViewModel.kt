package com.example.roombooking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombooking.DatabaseInstance
import com.example.roombooking.model.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val studentDao = DatabaseInstance.getDatabase(application).studentDao()

    fun registerStudent(student: Student, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                studentDao.insertStudent(student)
                onComplete(true)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }

    fun validateStudent(id: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val student = studentDao.getStudent(id, password)
            onResult(student != null)
        }
    }

    fun getAllStudents(onResult: (List<Student>) -> Unit) {
        viewModelScope.launch {
            val students = studentDao.getAllStudents()
            onResult(students)
        }
    }

}


