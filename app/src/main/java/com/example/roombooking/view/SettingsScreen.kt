package com.example.roombooking.view
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.roombooking.viewmodel.StudentViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    studentViewModel: StudentViewModel,
    studentId: String,
    onAccountDeleted: () -> Unit,
    paddingValues: PaddingValues
) {
    val uiState by studentViewModel.uiState

    LaunchedEffect(studentId) {
        studentViewModel.loadStudentDetails(studentId)
    }

    val gradientColors = listOf(
        Color(0xFF3064B8),
        Color(0xFFAAE5FF)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Brush.verticalGradient(gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Profile",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
            tint = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "User Profile",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = uiState.studentId,
            onValueChange = {},
            label = { Text("Student ID") },
            enabled = false,
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
                containerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name Field (editable)
        TextField(
            value = uiState.studentName,
            onValueChange = { newName ->
                studentViewModel.updateStudentDetails(newName, uiState.password) { success ->
                    if (success) {}
                }
            },
            label = { Text("Name") },
            enabled = uiState.isEditing,
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
                containerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = uiState.password,
            onValueChange = { newPassword ->
                studentViewModel.updateStudentDetails(uiState.studentName, newPassword) { success ->
                    if (success) {
                        // update the UI
                    }
                }
            },
            label = { Text("Password") },
            enabled = uiState.isEditing,
            modifier = Modifier.fillMaxWidth(0.9f),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
                containerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (!uiState.isEditing) {
            Button(
                onClick = { studentViewModel.toggleEditMode() },
                modifier = Modifier.fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3064B8))
            ) {
                Text("Edit", color = Color.White)
            }
        } else {
            Button(
                onClick = {
                    studentViewModel.updateStudentDetails(uiState.studentName, uiState.password) { success ->
                        if (success) {
                            // Optionally show a feedback here
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3064B8))
            ) {
                Text("Save", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { studentViewModel.deleteStudent(onAccountDeleted) },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3064B8))
        ) {
            Text("Delete Account", color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f)) // Push content to top
    }
}

//@Composable
//fun SettingsScreen(
//    studentViewModel: StudentViewModel,
//    studentId: String,
//    onAccountDeleted: () -> Unit,
//    paddingValues: PaddingValues
//) {
//    // Observe UI state from the ViewModel
//    val uiState by studentViewModel.uiState
//
//    // Fetch student details when the screen is loaded
//    LaunchedEffect(studentId) {
//        studentViewModel.loadStudentDetails(studentId)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(paddingValues),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Text(
//            text = "User Profile",
//            style = MaterialTheme.typography.headlineLarge,
//            modifier = Modifier.padding(16.dp),
//            color = MaterialTheme.colorScheme.primary
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display Student ID (non-editable)
//        TextField(
//            value = uiState.studentId,
//            onValueChange = {},
//            label = { Text("Student ID") },
//            enabled = false,
//            modifier = Modifier.fillMaxWidth(0.9f)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Name Field (editable)
//        TextField(
//            value = uiState.studentName,
//            onValueChange = { newName ->
//                studentViewModel.updateStudentDetails(newName, uiState.password) { success ->
//                    if (success) {
//                    }
//                }
//            },
//            label = { Text("Name") },
//            enabled = uiState.isEditing,
//            modifier = Modifier.fillMaxWidth(0.9f)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = uiState.password,
//            onValueChange = { newPassword ->
//                studentViewModel.updateStudentDetails(uiState.studentName, newPassword) { success ->
//                    if (success) {
//                        //  update the UI
//                    }
//                }
//            },
//            label = { Text("Password") },
//            enabled = uiState.isEditing,
//            modifier = Modifier.fillMaxWidth(0.9f),
//            visualTransformation = PasswordVisualTransformation()
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        if (!uiState.isEditing) {
//            Button(onClick = { studentViewModel.toggleEditMode() }) {
//                Text("Edit")
//            }
//        } else {
//            Button(onClick = {
//                studentViewModel.updateStudentDetails(uiState.studentName, uiState.password) { success ->
//                    if (success) {
//                        // Optionally show a feedback here
//                    }
//                }
//            }) {
//                Text("Save")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { studentViewModel.deleteStudent(onAccountDeleted) },
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//        ) {
//            Text("Delete Account")
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//    }
//}
