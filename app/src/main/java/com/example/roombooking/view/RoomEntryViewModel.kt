package com.example.roombooking.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roombooking.model.Room
import com.example.roombooking.data.RoomsRepository

/**
 * ViewModel to validate and insert rooms in the Room database.
 */
class RoomEntryViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {

    /**
     * Holds current room ui state
     */
    var roomUiState by mutableStateOf(RoomUiState())
        private set

    /**
     * Updates the [roomUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(roomDetails: RoomDetails) {
        roomUiState =
            RoomUiState(roomDetails = roomDetails, isEntryValid = validateInput(roomDetails))
    }

    /**
     * Inserts an [Room] in the Room database
     */
    suspend fun saveRoom() {
        if (validateInput()) {
            roomsRepository.insertRoom(roomUiState.roomDetails.toRoom())
        }
    }

    private fun validateInput(uiState: RoomDetails = roomUiState.roomDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && status.isNotBlank() && date.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for a Room.
 */
data class RoomUiState(
    val roomDetails: RoomDetails = RoomDetails(),
    val isEntryValid: Boolean = false
)

data class RoomDetails(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val date: String = "",
)

/**
 * Extension function to convert [RoomUiState] to [Room].
 */
fun RoomDetails.toRoom(): Room = Room(
    id = id,
    name = name,
    status = status,
    date = date
)

/**
 * Extension function to convert [Room] to [RoomUiState]
 */
fun Room.toRoomUiState(isEntryValid: Boolean = false): RoomUiState = RoomUiState(
    roomDetails = this.toRoomDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Room] to [RoomDetails]
 */
fun Room.toRoomDetails(): RoomDetails = RoomDetails(
    id = id,
    name = name,
    status = status,
    date = date
)









//
//import androidx.lifecycle.ViewModel
//import com.example.roombooking.model.Room
//import com.example.roombooking.data.RoomsRepository
//
///**
// * Represents Ui State for an Room.
// */
//data class RoomUiState(
//    val roomDetails: RoomDetails = RoomDetails(),
//    val isEntryValid: Boolean = false
//)
//
//data class RoomDetails(
//    val id: Int = 0,
//    val name: String,   // Room ID is room number (e.g., "P1105")
//    val status: String, // "available" or "booked"
//    val date: String    //  (e.g., "01/12/2024")
//)
//
///**
// * Extension function to convert [RoomDetails] to [Room]
// */
//fun RoomDetails.toRoom(): Room = Room(
//    id = id,
//    name = name,
//    status = status,
//    date = date
//)
//
///**
// * Extension function to convert [Room] to [RoomUiState]
// */
//fun Room.toRoomUiState(isEntryValid: Boolean = false): RoomUiState = RoomUiState(
//    roomDetails = this.toRoomDetails(),
//    isEntryValid = isEntryValid
//)
//
///**
// * Extension function to convert [Room] to [RoomDetails]
// */
//fun Room.toRoomDetails(): RoomDetails = RoomDetails(
//    id = id,
//    name = name,
//    status = status,
//    date = date
//)
//
//private fun validateInput(uiState: RoomDetails = roomUiState.roomDetails): Boolean {
//    return with(uiState) {
//        name.isNotBlank() && status.isNotBlank() && date.isNotBlank()
//    }
//}
//
//class RoomEntryViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {
//}
//
