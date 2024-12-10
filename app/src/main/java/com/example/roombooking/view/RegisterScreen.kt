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
import androidx.compose.ui.unit.sp
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
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Register",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            InputField(
                value = studentName,
                label = "Full Name",
                onValueChange = { studentName = it },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            InputField(
                value = studentId,
                label = "Student ID",
                onValueChange = { studentId = it },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            InputField(
                value = password,
                label = "Password",
                isPassword = true,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(0.9f)
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
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF3064B8)
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Register", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }

            Button(
                onClick = { onBackClicked() },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3064B8),
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Back", fontSize = 18.sp, fontWeight = FontWeight.Medium)
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


