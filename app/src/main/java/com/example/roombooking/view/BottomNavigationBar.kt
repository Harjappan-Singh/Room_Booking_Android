package com.example.roombooking.view

import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import com.example.roombooking.navigation.Routes
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Routes.Home to Icons.Default.Home,
        Routes.BookRoom to Icons.Default.AddCircle,
        Routes.Settings to Icons.Default.Edit,
        Routes.Students to Icons.Default.Person,
    )
    val darkBlue = Color(0xFF3064B8)
    val lightBlue = Color(0xFFAAE5FF)

    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Color.White,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    lightBlue,
                    darkBlue
                )
            )
        )
    ) {
        items.forEach { (route, icon) ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == route,
                onClick = { navController.navigate(route) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        route.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (navController.currentDestination?.route == route) darkBlue else Color.White
                    )
                }
            )
        }
    }
}
