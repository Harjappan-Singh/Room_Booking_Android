package com.example.roombooking.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmBookingScreen(
    roomId: String,
    date: String,
    studentId: String, // Pass the logged-in student ID
    onBookRoom: (String, String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Room ID: $roomId")
            Text(text = "Date: $date")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { onBookRoom(roomId, date, studentId) }) {
                Text(text = "Book Now")
            }
        }
    }
}
