package com.example.roombooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.roombooking.view.HomeScreen
import com.example.roombooking.view.LoginWithButtonAndImage
import com.example.roombooking.view.SplashScreen
import com.example.roombooking.viewmodel.RoomViewModel
import androidx.compose.ui.Modifier
import com.example.roombooking.viewmodel.AppViewModel


//class MainActivity : ComponentActivity() {
//    private val roomViewModel: RoomViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            var currentScreen by remember { mutableStateOf("Login") } // Tracks the current screen
//
//            when (currentScreen) {
//                "Login" -> {
//                    LoginWithButtonAndImage(
//                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
//                        onLoginClicked = { currentScreen = "Home" },
//                        onRegisterClicked = { currentScreen = "Register" }
//                    )
//                }
//                "Home" -> {
//                    val isLoading = roomViewModel.isLoading.collectAsState()
//                    if (isLoading.value) {
//                        SplashScreen()
//                    } else {
//                        HomeScreen(roomViewModel)
//                    }
//                }
//                "Register" -> {
//                    // You could navigate to another composable for user registration if needed.
//                    Text("Registration Screen - Under Construction")
//                }
//            }
//        }
//    }
//}

class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentScreen by appViewModel.currentScreen.collectAsState()
            val isAppLoading by appViewModel.isAppLoading.collectAsState()

            when (currentScreen) {
                "Splash" -> {
                    if (isAppLoading) SplashScreen()
                }
                "Login" -> {
                    LoginWithButtonAndImage(
                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
                        onLoginClicked = { appViewModel.navigateTo("Home") },
                        onRegisterClicked = { appViewModel.navigateTo("Register") }
                    )
                }
                "Home" -> {
                    HomeScreen(roomViewModel)
                }
                "Register" -> {
                    Text("Registration Screen - Under Construction")
                }
            }
        }
    }
}
