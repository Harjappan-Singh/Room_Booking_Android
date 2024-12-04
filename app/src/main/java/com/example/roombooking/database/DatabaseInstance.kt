package com.example.roombooking

import android.content.Context
import androidx.room.Room
import com.example.roombooking.database.AppDatabase

object DatabaseInstance {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val app = context.applicationContext as RoomBookingApp
            val instance = app.database
            INSTANCE = instance
            instance
        }
    }
}