package com.example.roombooking.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roombooking.navigation.Routes
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SplashScreen(navController: NavController, isAppLoading: Boolean) {
    if (!isAppLoading) {
        navController.navigate(Routes.LoginRegister) {
            popUpTo(Routes.Splash) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF3064B8), // Dark Blue
                        androidx.compose.ui.graphics.Color(0xFFAAE5FF)  // Light Blue
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Welcome to Space Reserve",
                color = androidx.compose.ui.graphics.Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Booking made simpler",
                color = androidx.compose.ui.graphics.Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            CircularProgressIndicator(
                color = androidx.compose.ui.graphics.Color.White,
                strokeWidth = 3.dp
            )
        }
    }
}

