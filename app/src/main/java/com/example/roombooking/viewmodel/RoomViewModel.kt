package com.example.roombooking.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.roombooking.model.Room
import com.example.roombooking.model.RoomRepository

class RoomViewModel : ViewModel() {

    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
    val roomList: StateFlow<List<Room>> get() = _roomList

    init {
        fetchRooms()
    }

    private fun fetchRooms() {
        _roomList.value = RoomRepository.getRooms()
    }
}