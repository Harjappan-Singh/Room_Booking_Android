package com.example.roombooking.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RoomApi {
    @GET("room_availability")
    suspend fun getRoomAvailability(
        @Query("date") date: String
    ): Map<String, List<Map<String, String>>>
}
