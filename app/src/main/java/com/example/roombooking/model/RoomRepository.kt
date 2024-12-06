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

    suspend fun bookRoom(roomId: String, date: String, studentId: String): Boolean {
        try {
            val response = RetrofitInstance.api.bookRoom(
                mapOf(
                    "room_id" to roomId,
                    "date" to date,
                    "student_id" to studentId
                )
            )
            return response["success"] == true
        } catch (e: Exception) {
            Log.e("RoomRepository", "Error booking room: ${e.message}", e)
            throw e
        }
    }

    suspend fun getBookingsForStudent(studentId: String): List<Pair<String, String>> {
        try {
            val response = RetrofitInstance.api.getBookings(studentId)
            Log.d("RoomRepository", "Bookings API Response: $response")

            val bookings = response["bookings"] as? List<Map<String, String>> ?: emptyList()

            return bookings.map { booking ->
                val date = booking["date"] ?: "Unknown date"
                val roomId = booking["room_id"] ?: "Unknown room"
                date to roomId
            }
        } catch (e: Exception) {
            Log.e("RoomRepository", "Error fetching bookings: ${e.message}", e)
            throw e
        }
    }
}