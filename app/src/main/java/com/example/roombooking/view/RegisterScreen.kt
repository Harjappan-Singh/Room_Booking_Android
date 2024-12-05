package com.example.roombooking.view
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(value = studentName, label = "Full Name") {
            studentName = it
        }

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
                studentName.isBlank() -> snackbarMessage = "Full Name is required!"
                studentId.isBlank() -> snackbarMessage = "Student ID is required!"
                password.isBlank() -> snackbarMessage = "Password is required!"
                password.length < 6 -> snackbarMessage = "Password must be at least 6 characters!"
                else -> {
                    val newStudent = Student(studentId = studentId, name = studentName, password = password)
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
        }) {
            Text("Register")
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


//@Composable
//fun RegisterScreen(
//    onRegisterClicked: (String, String, String) -> Unit = { _, _, _ -> },
//    onBackClicked: () -> Unit = {}
//) {
//    var studentName by remember { mutableStateOf("") }
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
//            text = "Register",
//            style = MaterialTheme.typography.headlineLarge,
//            color = MaterialTheme.colorScheme.primary
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        InputField(value = studentName, label = "Full Name") {
//            studentName = it
//        }
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
//        Button(onClick = { onRegisterClicked(studentName, studentId, password) }) {
//            Text("Register")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { onBackClicked() }) {
//            Text("Back")
//        }
//    }
//}
