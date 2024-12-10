package com.example.roombooking.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

    LaunchedEffect(Unit) {
        studentViewModel.getAllStudents { studentList ->
            students = studentList
        }
    }

    val gradientColors = listOf(
        Color(0xFF3064B8), // Dark blue
        Color(0xFFAAE5FF) // Light blue
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Brush.verticalGradient(gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Registered Students",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.White
            ),
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 56.dp)
        ) {
            items(students) { student ->
                StudentCard(
                    student = student,
                    onDelete = {
                        studentViewModel.deleteStudentWithId(student.studentId) { isDeleted ->
                            if (isDeleted) {
                                students = students.filter { it.studentId != student.studentId }
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
                .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3064B8),
                contentColor = Color.White
            )
        ) {
            Text("Back")
        }
    }
}