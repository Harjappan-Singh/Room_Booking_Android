package com.example.roombooking.data

import com.example.roombooking.model.Room
import com.example.roombooking.network.RoomApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomsRepositoryImpl(
    private val roomDao: RoomDao,
    private val apiService: RoomApiService
) : RoomsRepository {

    override fun getAllRoomsStream(): Flow<List<Room>> {
        return roomDao.getAllRooms() // Fetch from the Room database
    }

    override fun getRoomStream(id: Int): Flow<Room?> {
        return roomDao.getRoomById(id)
    }

    override suspend fun insertRoom(room: Room) {
        roomDao.insertRoom(room)
    }

    override suspend fun deleteRoom(room: Room) {
        roomDao.deleteRoom(room)
    }

    override suspend fun updateRoom(room: Room) {
        roomDao.updateRoom(room)
    }

    /**
     * Fetch rooms from the API and update the database.
     */
    suspend fun fetchAndStoreRoomsFromApi() {
        val roomsFromApi = apiService.getRooms() // Fetch data from Retrofit
        roomDao.insertRooms(roomsFromApi) // Save to Room database
    }
}