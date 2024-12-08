package com.example.roombooking.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roombooking.dao.StudentDao
import com.example.roombooking.model.Student

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
}
