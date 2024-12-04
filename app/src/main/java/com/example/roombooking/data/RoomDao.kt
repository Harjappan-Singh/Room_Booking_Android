package com.example.roombooking.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.example.roombooking.model.Room
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(room: Map<String, List<Map<String, String>>>)

    @Update
    suspend fun update(room: Room)

    @Delete
    suspend fun delete(room: Room)

    @Query("SELECT * from rooms WHERE id = :id")
    fun getRoom(id: Int): Flow<Map<String, List<Map<String, String>>>>

    @Query("SELECT * from rooms ORDER BY name ASC")
    fun getAllRooms(): Flow<Map<String, List<Map<String, String>>>>
}