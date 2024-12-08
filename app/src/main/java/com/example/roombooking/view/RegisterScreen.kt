package com.example.roombooking.view
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.roombooking.model.Student
import com.example.roombooking.viewmodel.StudentViewModel

@Composable
fun RegisterScreen(
    studentViewModel: StudentViewModel,
    onBackClicked: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var studentName by remember { mutableStateOf("") }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF3064B8), Color(0xFFAAE5FF))
                )
            )
    ) {
        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3064B8),
                    textAlign = TextAlign.Center
                )

                InputField(
                    value = studentName,
                    label = "Full Name",
                    onValueChange = { studentName = it },
                    modifier = Modifier.fillMaxWidth()
                )

                InputField(
                    value = studentId,
                    label = "Student ID",
                    onValueChange = { studentId = it },
                    modifier = Modifier.fillMaxWidth()
                )

                InputField(
                    value = password,
                    label = "Password",
                    isPassword = true,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        when {
                            studentName.isBlank() -> snackbarMessage = "Full Name is required!"
                            studentId.isBlank() -> snackbarMessage = "Student ID is required!"
                            password.isBlank() -> snackbarMessage = "Password is required!"
                            password.length < 6 -> snackbarMessage = "Password must be at least 6 characters!"
                            else -> {
                                val newStudent = Student(studentId, studentName, password)
                                studentViewModel.registerStudent(newStudent) { success ->
                                    if (success) {
                                        snackbarMessage = "Registered successfully!"
                                        onRegisterSuccess()
                                    } else {
                                        snackbarMessage = "Registration failed!"
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }

                TextButton(onClick = onBackClicked) {
                    Text("Back", color = MaterialTheme.colorScheme.primary)
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}
