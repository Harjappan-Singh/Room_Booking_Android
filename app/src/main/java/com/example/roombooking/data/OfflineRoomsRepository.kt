package com.example.roombooking.data

import com.example.roombooking.model.Room
import kotlinx.coroutines.flow.Flow

class OfflineRoomsRepository(private val roomDao: RoomDao) : RoomsRepository {
    override fun getAllRoomsStream(): Flow<List<Room>> = roomDao.getAllRooms()

    override fun getRoomStream(id: Int): Flow<Room?> = roomDao.getRoom(id)

    override suspend fun insertRoom(room: Room) = roomDao.insert(room)

    override suspend fun deleteRoom(room: Room) = roomDao.delete(room)

    override suspend fun updateRoom(room: Room) = roomDao.update(room)
}