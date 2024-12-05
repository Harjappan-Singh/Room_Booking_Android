package com.example.roombooking.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun MainApp(appViewModel: AppViewModel, roomViewModel: RoomViewModel, studentViewModel: StudentViewModel) {
    val navController = rememberNavController()
    val isAppLoading by appViewModel.isAppLoading.collectAsState()
    val studentId = studentViewModel.getStudentId() ?: "d00253215"

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
                studentViewModel = studentViewModel,
                onLoginSuccess = { navController.navigate(Routes.Home) },
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
                SettingsScreen(
                    studentViewModel = studentViewModel,
                    studentId = studentId,
                    onAccountDeleted = {
                        navController.navigate(Routes.Login)
                    },
                    paddingValues = contentPadding
                )
            }
        }

        composable(Routes.BookRoom) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { contentPadding ->
                BookRoomScreen(
                    paddingValues = contentPadding,
                    onDateSelected = { selectedDate ->
                        navController.navigate("${Routes.DisplayDate}/$selectedDate")
                    }
                )
            }
        }

        composable(
            route = "${Routes.DisplayDate}/{date}",
            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: "No date selected"
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { contentPadding ->
//                DisplayDateScreen(date = date)
                Box(modifier = Modifier.padding(contentPadding)) {
                    DisplayDateScreen(date = date)
                }
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
