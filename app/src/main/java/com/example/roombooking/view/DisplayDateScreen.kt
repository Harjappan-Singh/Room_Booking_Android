package com.example.roombooking.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roombooking.model.Room
import com.example.roombooking.viewmodel.RoomViewModel

@Composable
fun DisplayDateScreen(date: String, roomViewModel: RoomViewModel = viewModel(), navController: NavController) {
    val roomList by roomViewModel.roomList.collectAsState()
    val errorMessage by roomViewModel.errorMessage.collectAsState()

    LaunchedEffect(date) {
        roomViewModel.fetchRooms(date)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3064B8),
                        Color(0xFFAAE5FF)
                    )
                )
            )
    ) {
        when {
            errorMessage != null -> Text(
                text = errorMessage!!,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
            roomList.isEmpty() -> Text(
                text = "No available rooms",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
            else -> LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                items(roomList) { room ->
                    RoomItem(room) { selectedRoomId ->
                        navController.navigate("confirmBooking/$selectedRoomId/$date")
                    }
                }
            }
        }
    }
}

@Composable
fun RoomItem(room: Room, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(room.room_id) }
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF3064B8),
                        Color(0xFFAAE5FF)
                    )
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Room ID: ${room.room_id}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Text(
            text = "Status: ${room.status}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}
