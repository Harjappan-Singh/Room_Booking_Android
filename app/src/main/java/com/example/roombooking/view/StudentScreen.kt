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
import androidx.compose.foundation.lazy.items
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
fun StudentsScreen(
    studentViewModel: StudentViewModel,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    var students by remember { mutableStateOf<List<Student>>(emptyList()) }

    // Fetch students from the database
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(students) { student ->
                StudentCard(
                    student = student,
                    onDelete = { studentId ->
                        studentViewModel.deleteStudent(studentId) { success ->
                            if (success) {
                                students = students.filter { it.studentId != studentId }
                            }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text("Back")
        }
    }
}

