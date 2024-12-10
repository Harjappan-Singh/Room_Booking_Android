package com.example.roombooking.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombooking.DatabaseInstance
import com.example.roombooking.model.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

data class SettingsUiState(
    val studentId: String = "",
    val studentName: String = "",
    val password: String = "",
    val isEditing: Boolean = false,
    val snackbarMessage: String? = null
)

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val studentDao = DatabaseInstance.getDatabase(application).studentDao()

    val uiState = mutableStateOf(SettingsUiState())

    private val context = getApplication<Application>().applicationContext

    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // Save studentId
    fun saveStudentId(studentId: String) {
        sharedPreferences.edit().putString("studentId", studentId).apply()
    }

    // Retrieve studentId
    fun getStudentId(): String? {
        return sharedPreferences.getString("studentId", null)
    }

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

    fun getStudentById(studentId: String, onResult: (Student?) -> Unit) {
        viewModelScope.launch {
            val student = studentDao.getStudentById(studentId)
            onResult(student)
        }
    }

    fun loadStudentDetails(studentId: String) {
        viewModelScope.launch {
            val student = studentDao.getStudentById(studentId)
            if (student != null) {
                uiState.value = uiState.value.copy(
                    studentId = student.studentId,
                    studentName = student.name,
                    password = student.password
                )
            }
        }
    }

    fun toggleEditMode() {
        uiState.value = uiState.value.copy(isEditing = !uiState.value.isEditing)
    }

    fun updateStudentDetails(newName: String, newPassword: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val studentId = uiState.value.studentId
            val success = studentDao.updateStudentDetails(studentId, newName, newPassword) > 0
            if (success) {
                uiState.value = uiState.value.copy(
                    studentName = newName,
                    password = newPassword,
                    snackbarMessage = "Profile updated successfully!",
//                    isEditing = false
                )
            } else {
                uiState.value = uiState.value.copy(snackbarMessage = "Update failed.")
            }
            onResult(success)
        }
    }

    fun deleteStudent(onAccountDeleted: () -> Unit) {
        viewModelScope.launch {
            val success = studentDao.deleteStudent(uiState.value.studentId) > 0
            if (success) {
                onAccountDeleted()
            } else {
                uiState.value = uiState.value.copy(snackbarMessage = "Failed to delete account.")
            }
        }
    }

    fun deleteStudentWithId(studentId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                studentDao.deleteStudent(studentId)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun dismissSnackbar() {
        uiState.value = uiState.value.copy(snackbarMessage = null)
    }
}
