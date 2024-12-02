package com.example.roombooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.example.roombooking.view.HomeScreen
import com.example.roombooking.view.SplashScreen
import com.example.roombooking.viewmodel.RoomViewModel

class MainActivity : ComponentActivity() {
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoading = roomViewModel.isLoading.collectAsState()
            if (isLoading.value) {
                SplashScreen()
            } else {
                HomeScreen(roomViewModel)
            }
        }
    }
}

