package com.example.roombooking.data

import com.example.roombooking.model.Student
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * from students WHERE id = :id")
    fun getStudent(id: Int): Flow<Student>

    @Query("SELECT * from students ORDER BY name ASC")
    fun getAllStudents(): Flow<List<Student>>
}