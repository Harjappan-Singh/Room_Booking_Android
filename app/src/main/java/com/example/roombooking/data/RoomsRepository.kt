package com.example.roombooking.data

import com.example.roombooking.model.Room
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Room] from a given data source.
 */
interface RoomsRepository {
    /**
     * Retrieve all the rooms from the the given data source.
     */
    fun getAllRoomsStream(): Flow<List<Room>>

    /**
     * Retrieve a room from the given data source that matches with the [id].
     */
    fun getRoomStream(id: Int): Flow<Room?>

    /**
     * Insert room in the data source
     */
    suspend fun insertRoom(room: Room)

    /**
     * Delete room from the data source
     */
    suspend fun deleteRoom(room: Room)

    /**
     * Update room in the data source
     */
    suspend fun updateRoom(room: Room)
}

