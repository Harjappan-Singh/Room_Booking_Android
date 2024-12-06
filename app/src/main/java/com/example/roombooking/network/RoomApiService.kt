package com.example.roombooking.network

import com.example.roombooking.model.Room
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface RoomApi {
    @GET("room_availability")   // This appends "room_availability" to the BASE_URL
    suspend fun getRoomAvailability(
        @Query("date") date: String // This appends "?date=..." to the URL
    ): RoomApiResponse

    @GET
    suspend fun testApiWithFullUrl(
        @Url fullUrl: String // Manually pass the full URL
    ): RoomApiResponse

    @POST("book_room")
    suspend fun bookRoom(@Body requestBody: Map<String, String>): Map<String, Any>

    @GET("my_bookings")
    suspend fun getBookings(@Query("student_id") studentId: String): Map<String, Any>
}
