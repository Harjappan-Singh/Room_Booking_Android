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

            val apiResponse = RetrofitInstance.api.getRoomAvailability(date)
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

    suspend fun getBookingsForStudent(studentId: String): List<Room> {
        try {
            val response = RetrofitInstance.api.getBookings(studentId)
            val bookings = response["bookings"] as? List<Map<String, Any>> ?: emptyList()

            return bookings.map { booking ->
                Room(
                    room_id = booking["room_id"] as String,
                    status = booking["status"] as String,
                    student_id = studentId,
                    image_url = booking["image_url"] as String
                )
            }
        } catch (e: Exception) {
            Log.e("RoomRepository", "Error fetching bookings: ${e.message}", e)
            throw e
        }
    }
}