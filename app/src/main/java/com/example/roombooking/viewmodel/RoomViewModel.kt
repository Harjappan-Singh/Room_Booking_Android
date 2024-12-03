package com.example.roombooking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.roombooking.model.Room
import com.example.roombooking.model.RoomRepository
import kotlinx.coroutines.launch

//class RoomViewModel : ViewModel() {
//
//    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
//    val roomList: StateFlow<List<Room>> get() = _roomList
//
//    init {
//        fetchRooms()
//    }
//
//    private fun fetchRooms() {
//        viewModelScope.launch {
//            _roomList.value = RoomRepository.getRooms()
//        }
//    }
//}


class RoomViewModel : ViewModel() {

    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
    val roomList: StateFlow<List<Room>> get() = _roomList

    init {
        fetchRooms("01/12/2024")
    }

    private fun fetchRooms(date: String) {
        viewModelScope.launch {
            try {
                _roomList.value = RoomRepository.getRooms(date)
            } catch (e: Exception) {
                // Handle error, e.g., network failure, API issues, etc.
                _roomList.value = emptyList() // Or set an error state
            }
        }
    }
}