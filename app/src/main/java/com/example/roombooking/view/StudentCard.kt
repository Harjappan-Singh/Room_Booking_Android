package com.example.roombooking.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.roombooking.model.Student


@Composable
fun StudentCard(student: Student, onDelete: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("ID: ${student.studentId}", style = MaterialTheme.typography.bodyMedium)
                Text("Name: ${student.name}", style = MaterialTheme.typography.bodyMedium)
            }

            Button(
                onClick = { onDelete(student.studentId) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3064B8))
            ) {
                Text("Delete")
            }
        }
    }
}
