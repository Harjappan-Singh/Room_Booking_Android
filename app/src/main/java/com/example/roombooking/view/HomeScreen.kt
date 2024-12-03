package com.example.roombooking.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roombooking.viewmodel.RoomViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: RoomViewModel) {
    val roomList by viewModel.roomList.collectAsState()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Booked Rooms") },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            items(roomList) { room ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Room ID: ${room.id}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Status: ${room.status}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = RoomViewModel())
}