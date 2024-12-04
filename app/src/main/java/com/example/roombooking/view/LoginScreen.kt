package com.example.roombooking.view
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roombooking.viewmodel.StudentViewModel

@Composable
fun LoginScreen(
    studentViewModel: StudentViewModel,
    onLoginClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    var studentId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) } // Tracks login error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(value = studentId, label = "Student ID") {
            studentId = it
            loginError = false // Reset error on input change
        }

        Spacer(modifier = Modifier.height(16.dp))

        InputField(value = password, label = "Password", isPassword = true) {
            password = it
            loginError = false // Reset error on input change
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loginError) {
            Text(
                text = "Invalid credentials, please try again.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            studentViewModel.validateStudent(studentId, password) { success ->
                if (success) {
                    onLoginClicked() // Proceed on successful login
                } else {
                    loginError = true // Show error message
                }
            }
        }) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onBackClicked() }) {
            Text("Back")
        }
    }
}


//@Composable
//fun LoginScreen(
//    onLoginClicked: (String, String) -> Unit = { _, _ -> },
//    onBackClicked: () -> Unit = {}
//) {
//    var studentId by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Login",
//            style = MaterialTheme.typography.headlineLarge,
//            color = MaterialTheme.colorScheme.primary
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        InputField(value = studentId, label = "Student ID") {
//            studentId = it
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        InputField(value = password, label = "Password", isPassword = true) {
//            password = it
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { onLoginClicked(studentId, password) }) {
//            Text("Log In")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { onBackClicked() }) {
//            Text("Back")
//        }
//    }
//}
