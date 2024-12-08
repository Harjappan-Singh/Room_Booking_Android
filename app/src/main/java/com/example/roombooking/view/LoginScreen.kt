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
    onLoginSuccess: () -> Unit,
    onBackClicked: () -> Unit
) {
    var studentId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            snackbarMessage = null
        }
    }

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
        }

        Spacer(modifier = Modifier.height(16.dp))

        InputField(value = password, label = "Password", isPassword = true) {
            password = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            when {
                studentId.isBlank() -> snackbarMessage = "Student ID is required!"
                password.isBlank() -> snackbarMessage = "Password is required!"
                else -> {
                    studentViewModel.validateStudent(studentId, password) { success ->
                        if (success) {
                            snackbarMessage = "Login successful!"
                            onLoginSuccess()
                        } else {
                            snackbarMessage = "Invalid credentials!"
                        }
                    }
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

    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}
