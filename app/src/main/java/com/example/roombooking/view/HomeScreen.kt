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
fun CompactLayout(bookingsList: List<Pair<String, String>>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(bookingsList) { booking ->
            val (date, roomId) = booking
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Room ID: $roomId", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Date: $date", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun ExpandedLayout(bookingsList: List<Pair<String, String>>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bookingsList) { booking ->
            val (date, roomId) = booking
            ElevatedCard(
                modifier = Modifier
                    .width(200.dp) // Fixed card width for larger screens
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Room ID: $roomId", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Date: $date", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(viewModel: RoomViewModel, studentId: String, paddingValues: PaddingValues) {
//    val bookingsList by viewModel.bookingsList.collectAsState()
//    val errorMessage by viewModel.errorMessage.collectAsState()
//
//    LaunchedEffect(studentId) {
//        viewModel.fetchBookings(studentId)
//    }
//
//    Scaffold(
//        topBar = {
//            SmallTopAppBar(
//                title = { Text("My Booked Rooms") },
//                colors = TopAppBarDefaults.smallTopAppBarColors()
//            )
//        }
//    ) { innerPadding ->
//        val combinedPadding = PaddingValues(
//            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current) + paddingValues.calculateStartPadding(LocalLayoutDirection.current),
//            top = innerPadding.calculateTopPadding() + paddingValues.calculateTopPadding(),
//            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current) + paddingValues.calculateEndPadding(LocalLayoutDirection.current),
//            bottom = innerPadding.calculateBottomPadding() + paddingValues.calculateBottomPadding()
//        )
//
//        if (errorMessage != null) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(combinedPadding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = errorMessage ?: "An error occurred")
//            }
//        } else if (bookingsList.isEmpty()) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(combinedPadding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text("No bookings found")
//            }
//        } else {
//            LazyColumn(modifier = Modifier.padding(combinedPadding).padding(16.dp)) {
//                items(bookingsList) { booking ->
//                    val (date, roomId) = booking
//                    ElevatedCard(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp)
//                    ) {
//                        Column(
//                            modifier = Modifier.padding(16.dp)
//                        ) {
//                            Text(text = "Room ID: $roomId", style = MaterialTheme.typography.titleMedium)
//                            Text(text = "Date: $date", style = MaterialTheme.typography.bodyMedium)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
