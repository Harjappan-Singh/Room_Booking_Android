package com.example.roombooking.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.roombooking.navigation.Routes

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Routes.Home to Icons.Default.Home,
        Routes.BookRoom to Icons.Default.AccountBox,
        Routes.Settings to Icons.Default.Settings,

    )

    NavigationBar {
        items.forEach { (route, icon) ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == route,
                onClick = { navController.navigate(route) },
                icon = { Icon(icon, contentDescription = null) },
                label = { Text(route.capitalize()) }
            )
        }
    }
}
