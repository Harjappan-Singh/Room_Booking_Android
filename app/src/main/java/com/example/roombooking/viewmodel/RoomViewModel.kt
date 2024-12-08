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

    // State for room list based on date
    private val _roomList = MutableStateFlow<List<Room>>(emptyList())
    val roomList: StateFlow<List<Room>> get() = _roomList

    // State for error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // State for booking result
    private val _bookingResult = MutableStateFlow<String?>(null)
    val bookingResult: StateFlow<String?> get() = _bookingResult

    // State for bookings specific to a student
    private val _bookingsList = MutableStateFlow<List<Room>>(emptyList()) // Change type to List<Room>
    val bookingsList: StateFlow<List<Room>> get() = _bookingsList

    // Fetch rooms based on a specific date
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

    // Book a specific room
    fun bookRoom(roomId: String, date: String, studentId: String) {
        viewModelScope.launch {
            try {
                val success = repository.bookRoom(roomId, date, studentId)
                _bookingResult.value = if (success) "Room booked successfully!" else "Failed to book room."
            } catch (e: Exception) {
                _bookingResult.value = "Error: ${e.message}"
            }
        }
    }

    // Fetch bookings for a specific student
    fun fetchBookings(studentId: String) {
        viewModelScope.launch {
            try {
                _bookingsList.value = repository.getBookingsForStudent(studentId)
                _errorMessage.value = null // Clear previous errors
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch bookings: ${e.message}"
            }
        }
    }
}