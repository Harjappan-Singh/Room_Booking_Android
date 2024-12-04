package com.example.roombooking.data

import com.example.roombooking.model.Student
import kotlinx.coroutines.flow.Flow

class OfflineStudentsRepository(private val studentDao: StudentDao) : StudentsRepository {
    override fun getAllStudentsStream(): Flow<List<Student>> = studentDao.getAllStudents()

    override fun getStudentStream(id: Int): Flow<Student?> = studentDao.getStudent(id)

    override suspend fun insertStudent(student: Student) = studentDao.insert(student)

    override suspend fun deleteStudent(student: Student) = studentDao.delete(student)

    override suspend fun updateStudent(student: Student) = studentDao.update(student)
}