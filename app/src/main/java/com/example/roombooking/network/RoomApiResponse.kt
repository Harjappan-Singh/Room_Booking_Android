package com.example.roombooking.network

import com.example.roombooking.model.Room

data class RoomApiResponse(
    val date: String,
    val rooms: List<Room>
)
