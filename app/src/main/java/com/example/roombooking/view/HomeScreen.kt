package com.example.roombooking.view

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
                .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3064B8),
                        Color(0xFFAAE5FF)
                    )
                )
            )
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
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Image covering around 80% of the card
                    AsyncImage(
                        model = room.image_url,
                        contentDescription = "Room Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // Adjust image height
                            .clip(MaterialTheme.shapes.medium)
                            .align(Alignment.TopCenter) // Align image to top
                    )
                    // Room ID on the bottom left
                    Text(
                        text = "Room ID: ${room.room_id}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    )
                    // Status on the bottom right
                    Text(
                        text = "Status: ${room.status}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    )
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
                    .width(200.dp)
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = room.image_url,
                        contentDescription = "Room Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = "Room ID: ${room.room_id}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    )
                    Text(
                        text = "Status: ${room.status}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
