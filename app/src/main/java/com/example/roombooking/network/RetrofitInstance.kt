package com.example.roombooking.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://reserve-space-api.onrender.com/"  // Ensure the trailing slash is present


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Logs requests and responses for debugging
        .build()

    val api: RoomApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Attach logging interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RoomApi::class.java)
    }
}
