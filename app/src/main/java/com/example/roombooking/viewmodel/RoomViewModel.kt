package com.example.roombooking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.roombooking.model.Room
import com.example.roombooking.model.RoomRepository
import kotlinx.coroutines.launch


class RoomViewModel : ViewModel() {
    private val repository = RoomRepository()

    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
    val roomList: StateFlow<List<Room>> get() = _roomList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun fetchRooms(date: String) {
        viewModelScope.launch {
            try {
                _roomList.value = repository.getRooms(date)
                _errorMessage.value = null // Clear previous errors
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load rooms: ${e.message}"
            }
        }
    }
//    private val repository = RoomRepository()
//
//    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
//    val roomList: StateFlow<List<Room>> get() = _roomList
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: StateFlow<String?> get() = _errorMessage
//
//    fun fetchRooms(date: String) {
//        viewModelScope.launch {
//            try {
//                _roomList.value = repository.getRooms(date)
//            } catch (e: Exception) {
//                _errorMessage.value = "Failed to load rooms: ${e.message}"
//            }
//        }
//    }
}