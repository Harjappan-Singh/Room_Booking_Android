package com.example.roombooking.view
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun BookRoomScreen(
    paddingValues: PaddingValues,
    onDateSelected: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Date picker dialog
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val date = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)
            selectedDate = date
            onDateSelected(date) // Trigger navigation with the selected date
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(modifier = Modifier.padding(paddingValues)
                            .fillMaxSize()
                            .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF3064B8),
                    Color(0xFFAAE5FF)
                )
            )
        )) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selected Date: $selectedDate",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { datePickerDialog.show() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3064B8),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "Pick a Date",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
