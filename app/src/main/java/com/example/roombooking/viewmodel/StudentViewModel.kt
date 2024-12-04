package com.example.roombooking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roombooking.data.StudentsRepository
import com.example.roombooking.model.Student

/**
 * ViewModel to validate and insert students in the Room database.
 */
class StudentViewModel(private val studentsRepository: StudentsRepository) : ViewModel() {

    /**
     * Holds current student ui state
     */
    var studentUiState by mutableStateOf(StudentUiState())
        private set

    /**
     * Updates the [studentUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(studentDetails: StudentDetails) {
        studentUiState =
            StudentUiState(studentDetails = studentDetails, isEntryValid = validateInput(studentDetails))
    }

    /**
     * Inserts an [Student] in the Room database
     */
    suspend fun saveStudent() {
        if (validateInput()) {
            studentsRepository.insertStudent(studentUiState.studentDetails.toStudent())
        }
    }

    private fun validateInput(uiState: StudentDetails = studentUiState.studentDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && studentNumber.isNotBlank() && password.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Student.
 */
data class StudentUiState(
    val studentDetails: StudentDetails = StudentDetails(),
    val isEntryValid: Boolean = false
)

data class StudentDetails(
    val id: Int = 0,
    val name: String = "",
    val studentNumber: String = "",
    val password: String = "",
)

/**
 * Extension function to convert [StudentUiState] to [Student]. If the value of [StudentDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [StudentUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun StudentDetails.toStudent(): Student = Student(
    id = id,
    name = name,
    studentNumber = studentNumber,
    password = password
)

//fun Student.formatedPrice(): String {
//    return NumberFormat.getCurrencyInstance().format(price) dont need this
//}

/**
 * Extension function to convert [Student] to [StudentUiState]
 */
fun Student.toStudentUiState(isEntryValid: Boolean = false): StudentUiState = StudentUiState(
    studentDetails = this.toStudentDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Student] to [StudentDetails]
 */
fun Student.toStudentDetails(): StudentDetails = StudentDetails(
    id = id,
    name = name,
    studentNumber = studentNumber,
    password = password
)