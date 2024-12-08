package com.example.roombooking.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConfirmBookingScreen(
    roomId: String,
    date: String,
    studentId: String,
    onBookRoom: (String, String, String) -> Unit,
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }

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
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Room ID: $roomId",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = "Date: $date",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onBookRoom(roomId, date, studentId)
                    showDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF3064B8)
                )
            ) {
                Text(text = "Book Now")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Do nothing to prevent accidental dismissal */ },
            title = {
                Text(
                    text = "Booking Successful",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF3064B8)
                )
            },
            text = {
                Text(
                    text = "Your booking for Room $roomId on $date was successful.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3064B8))
                ) {
                    Text("OK", color = Color.White)
                }
            }
        )
    }
}
