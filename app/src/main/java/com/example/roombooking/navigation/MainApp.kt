package com.example.roombooking.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roombooking.view.*
import com.example.roombooking.viewmodel.AppViewModel
import com.example.roombooking.viewmodel.RoomViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.roombooking.model.Student
import com.example.roombooking.viewmodel.StudentViewModel

@Composable
fun MainApp(appViewModel: AppViewModel, roomViewModel: RoomViewModel, studentViewModel: StudentViewModel) {
    val navController = rememberNavController()
    val isAppLoading by appViewModel.isAppLoading.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable(Routes.Splash) { SplashScreen(navController = navController, isAppLoading = isAppLoading) }
        composable(Routes.LoginRegister) {
            LoginWithButtonAndImage(
                onLoginClicked = { navController.navigate(Routes.Login) },
                onRegisterClicked = { navController.navigate(Routes.Register) },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Routes.Login) {
            LoginScreen(
                studentViewModel = studentViewModel, // Pass studentViewModel here
                onLoginClicked = { navController.navigate(Routes.Home) },
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable(Routes.Register) {
            RegisterScreen(
                studentViewModel = studentViewModel,
                onBackClicked = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate(Routes.Home) }
            )
        }
        composable(Routes.Home) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { contentPadding ->
                HomeScreen(roomViewModel, contentPadding)
            }
        }

        composable(Routes.Settings) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { contentPadding ->
                SettingsScreen(contentPadding)
            }
        }

        composable(Routes.BookRoom) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { contentPadding ->
                BookRoomScreen(contentPadding)
            }
        }

        composable(Routes.Students) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { contentPadding ->
                StudentsScreen(studentViewModel = studentViewModel, navController = navController, contentPadding)
            }
        }
    }
}
