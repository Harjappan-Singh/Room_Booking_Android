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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.roombooking.viewmodel.StudentViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SettingsScreen(
    studentViewModel: StudentViewModel,
    studentId: String,
    onAccountDeleted: () -> Unit,
    paddingValues: PaddingValues
) {
    // Observe UI state from the ViewModel
    val uiState by studentViewModel.uiState

    // Fetch student details when the screen is loaded
    LaunchedEffect(studentId) {
        studentViewModel.loadStudentDetails(studentId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "User Profile",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display Student ID (non-editable)
        TextField(
            value = uiState.studentId,
            onValueChange = {},
            label = { Text("Student ID") },
            enabled = false,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name Field (editable)
        TextField(
            value = uiState.studentName,
            onValueChange = { newName ->
                studentViewModel.updateStudentDetails(newName, uiState.password) { success ->
                    if (success) {
                    }
                }
            },
            label = { Text("Name") },
            enabled = uiState.isEditing,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = uiState.password,
            onValueChange = { newPassword ->
                studentViewModel.updateStudentDetails(uiState.studentName, newPassword) { success ->
                    if (success) {
                        //  update the UI
                    }
                }
            },
            label = { Text("Password") },
            enabled = uiState.isEditing,
            modifier = Modifier.fillMaxWidth(0.9f),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (!uiState.isEditing) {
            Button(onClick = { studentViewModel.toggleEditMode() }) {
                Text("Edit")
            }
        } else {
            Button(onClick = {
                studentViewModel.updateStudentDetails(uiState.studentName, uiState.password) { success ->
                    if (success) {
                        // Optionally show a feedback here
                    }
                }
            }) {
                Text("Save")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { studentViewModel.deleteStudent(onAccountDeleted) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Delete Account")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
