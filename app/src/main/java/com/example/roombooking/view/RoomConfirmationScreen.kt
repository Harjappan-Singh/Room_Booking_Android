package com.example.roombooking.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConfirmBookingScreen(
    roomId: String,
    date: String,
    studentId: String, // Pass the logged-in student ID
    onBookRoom: (String, String, String) -> Unit,
    navController: NavController // Pass the NavController for navigation
) {
    var showDialog by remember { mutableStateOf(false) }

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

            Button(onClick = {
                onBookRoom(roomId, date, studentId)
                showDialog = true // Show success dialog
            }) {
                Text(text = "Book Now")
            }
        }
    }

    // Show success dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Do nothing to prevent accidental dismissal */ },
            title = { Text("Booking Successful") },
            text = { Text("Your booking for Room $roomId on $date was successful.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    navController.navigate("home") { // Navigate to HomeScreen
                        popUpTo("home") { inclusive = true } // Clear backstack
                    }
                }) {
                    Text("OK")
                }
            }
        )
    }
}
