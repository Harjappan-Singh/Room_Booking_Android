package com.example.roombooking.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.roombooking.model.Student
import com.example.roombooking.viewmodel.StudentViewModel

@Composable
fun StudentsScreen(studentViewModel: StudentViewModel, navController: NavHostController, paddingValues: PaddingValues) {
    var students by remember { mutableStateOf<List<Student>>(emptyList()) }

    LaunchedEffect(Unit) {
        studentViewModel.getAllStudents { studentList ->
            students = studentList
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Registered Students",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        if (students.isNotEmpty()) {
            students.forEach { student ->
                Text("ID: ${student.studentId}, Name: ${student.name}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else {
            Text("No students registered.")
        }

        Spacer(modifier = Modifier.weight(1f)) // Push the button to the bottom

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.5f) // Make the button take up half the width
        ) {
            Text("Back")
        }
    }
}
