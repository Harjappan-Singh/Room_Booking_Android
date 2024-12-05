package com.example.roombooking.model

import android.util.Log
import com.example.roombooking.network.RetrofitInstance
import com.example.roombooking.model.Room
import com.example.roombooking.network.RoomApi
import java.net.UnknownHostException

class RoomRepository {

    suspend fun getRooms(date: String): List<Room> {
        try {
            Log.d("RoomRepository", "Fetching rooms for date: $date")

            // Fetch the API response
            val fullUrl = "https://reserve-space-api.onrender.com/room_availability?date=2024-12-01"
            val apiResponse = RetrofitInstance.api.testApiWithFullUrl(fullUrl)


            //val apiResponse = RetrofitInstance.api.getRoomAvailability(date)
            Log.d("RoomRepository", "Fetching rooms for date: $date")
            Log.d("RoomRepository", "API Response: $apiResponse")

            // Filter only available rooms
            return apiResponse.rooms.filter { room ->
                room.status == "Available"
            }
        } catch (e: Exception) {
            Log.e("RoomRepository", "Error fetching rooms: ${e.message}", e)
            throw e
        }
    }
}