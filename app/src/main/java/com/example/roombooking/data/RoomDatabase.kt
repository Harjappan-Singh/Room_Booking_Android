package com.example.roombooking.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Room::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() { //The first RoomDatabase refers to this kt, the second refers to extend to RoomDatabase

    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var Instance: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RoomDatabase::class.java, "room_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}