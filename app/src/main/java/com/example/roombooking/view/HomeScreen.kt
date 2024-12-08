package com.example.roombooking.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roombooking.viewmodel.RoomViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.roombooking.model.Room

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: RoomViewModel, studentId: String, paddingValues: PaddingValues) {
    val bookingsList by viewModel.bookingsList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(studentId) {
        viewModel.fetchBookings(studentId)
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("My Booked Rooms") },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        }
    ) { innerPadding ->
        val combinedPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current) + paddingValues.calculateStartPadding(LocalLayoutDirection.current),
            top = innerPadding.calculateTopPadding() + paddingValues.calculateTopPadding(),
            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current) + paddingValues.calculateEndPadding(LocalLayoutDirection.current),
            bottom = innerPadding.calculateBottomPadding() + paddingValues.calculateBottomPadding()
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(combinedPadding)
        ) {
            val isCompact = maxWidth < 600.dp // Define breakpoint for compact layout

            if (errorMessage != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = errorMessage ?: "An error occurred")
                }
            } else if (bookingsList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No bookings found")
                }
            } else {
                if (isCompact) {
                    CompactLayout(bookingsList) // For smaller screens
                } else {
                    ExpandedLayout(bookingsList) // For larger screens
                }
            }
        }
    }
}
@Composable
fun CompactLayout(roomList: List<Room>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(roomList) { room ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Display the image
                    AsyncImage(
                        model = room.image_url,
                        contentDescription = "Room Image",
                        modifier = Modifier
                            .size(64.dp)
                            .padding(end = 16.dp)
                    )
                    // Display room details
                    Column {
                        Text(text = "Room ID: ${room.room_id}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Status: ${room.status}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandedLayout(roomList: List<Room>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(roomList) { room ->
            ElevatedCard(
                modifier = Modifier
                    .width(200.dp) // Fixed card width for larger screens
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Display the image
                    AsyncImage(
                        model = room.image_url,
                        contentDescription = "Room Image",
                        modifier = Modifier
                            .size(128.dp)
                            .padding(bottom = 16.dp)
                    )
                    // Display room details
                    Text(text = "Room ID: ${room.room_id}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Status: ${room.status}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}