package com.example.roombooking.data

import com.example.roombooking.model.Student
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Student] from a given data source.
 */
interface StudentsRepository {
    /**
     * Retrieve all the students from the the given data source.
     */
    fun getAllStudentsStream(): Flow<List<Student>>

    /**
     * Retrieve a student from the given data source that matches with the [id].
     */
    fun getStudentStream(id: Int): Flow<Student?>

    /**
     * Insert student in the data source
     */
    suspend fun insertStudent(student: Student)

    /**
     * Delete student from the data source
     */
    suspend fun deleteStudent(student: Student)

    /**
     * Update student in the data source
     */
    suspend fun updateStudent(student: Student)
}
