package com.example.roombooking.data
import com.example.roombooking.model.Student

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var Instance: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StudentDatabase::class.java, "student_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}