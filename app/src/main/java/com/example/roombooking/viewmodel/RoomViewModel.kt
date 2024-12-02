package com.example.roombooking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.roombooking.model.Room
import com.example.roombooking.model.RoomRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {

    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
    val roomList: StateFlow<List<Room>> get() = _roomList

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        fetchRooms()
    }

    private fun fetchRooms() {
        viewModelScope.launch {
            delay(3000)
            _roomList.value = RoomRepository.getRooms()
            _isLoading.value = false
        }
    }
}
