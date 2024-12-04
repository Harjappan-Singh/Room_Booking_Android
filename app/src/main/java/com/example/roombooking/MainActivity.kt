package com.example.roombooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.roombooking.viewmodel.RoomViewModel
import com.example.roombooking.navigation.MainApp
import com.example.roombooking.viewmodel.AppViewModel
import com.example.roombooking.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()
    private val roomViewModel: RoomViewModel by viewModels()
    private val studentViewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(appViewModel, roomViewModel, studentViewModel)
        }
    }
}

