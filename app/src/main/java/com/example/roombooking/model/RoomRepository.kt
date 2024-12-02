package com.example.roombooking.model

object RoomRepository {
    fun getRooms(): List<Room> {
        return listOf(
            Room("P1105", "available"),
            Room("P1107", "booked"),
            Room("P1108", "available")
        )
    }
}