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
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(viewModel: RoomViewModel) {
//    val roomList by viewModel.roomList.collectAsState()
//
//    Scaffold(
//        topBar = {
//            SmallTopAppBar(
//                title = { Text("Booked Rooms") },
//                colors = TopAppBarDefaults.smallTopAppBarColors()
//            )
//        }
//    ) { innerPadding ->
//        LazyColumn(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
//            items(roomList) { room ->
//                ElevatedCard(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp)
//                ) {
//                    Column(
//                        modifier = Modifier.padding(16.dp)
//                    ) {
//                        Text(text = "Room ID: ${room.id}", style = MaterialTheme.typography.titleMedium)
//                        Text(text = "Status: ${room.status}", style = MaterialTheme.typography.bodyMedium)
//                    }
//                }
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: RoomViewModel, paddingValues: PaddingValues) {
    // Collect room list state from ViewModel
    val roomList by viewModel.roomList.collectAsState()

    // Define a loading state
    val isLoading = roomList.isEmpty()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Booked Rooms") },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        }
    ) { innerPadding ->
        // Merge innerPadding from Scaffold with paddingValues passed to the function
        val combinedPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current) + paddingValues.calculateStartPadding(LocalLayoutDirection.current),
            top = innerPadding.calculateTopPadding() + paddingValues.calculateTopPadding(),
            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current) + paddingValues.calculateEndPadding(LocalLayoutDirection.current),
            bottom = innerPadding.calculateBottomPadding() + paddingValues.calculateBottomPadding()
        )

        // Show a loading indicator while the data is being fetched
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(combinedPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator() // Show a loading spinner
            }
        } else {
            // Show the room list once the data is available
            LazyColumn(modifier = Modifier.padding(combinedPadding).padding(16.dp)) {
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

        // Optional: Error or empty state message
        if (roomList.isEmpty() && !isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(combinedPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No rooms available or error fetching data", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
