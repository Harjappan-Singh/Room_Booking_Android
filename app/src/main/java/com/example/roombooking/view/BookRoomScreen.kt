package com.example.roombooking.view
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text

@Composable
fun BookRoomScreen(paddingValues: PaddingValues) {
    Box(modifier = Modifier.padding(paddingValues)) {
        Text("Book Room Screen")
    }
}