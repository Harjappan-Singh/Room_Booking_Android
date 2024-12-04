package com.example.roombooking

import android.app.Application
import androidx.room.Room
import com.example.roombooking.database.AppDatabase

class RoomBookingApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "room_booking_database"
        ).build()
    }
}