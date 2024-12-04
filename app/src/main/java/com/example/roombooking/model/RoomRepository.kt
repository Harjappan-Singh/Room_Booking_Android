package com.example.roombooking.model
import com.example.roombooking.network.RetrofitInstance
import com.example.roombooking.model.Room
import com.example.roombooking.network.RoomApi

object RoomRepository {
    suspend fun getRooms(date: String): List<Room> {
        val apiResponse = RetrofitInstance.api.getRoomAvailability(date)
        val roomData = apiResponse[date] ?: emptyList()

        return roomData.map { roomEntry ->
            val (id, status) = roomEntry.entries.first()
            Room(id, status)
        }
    }
}
