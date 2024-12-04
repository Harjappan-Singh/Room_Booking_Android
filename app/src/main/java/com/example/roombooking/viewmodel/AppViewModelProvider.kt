package com.example.roombooking.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roombooking.StudentApplication
import com.example.roombooking.viewmodel.StudentViewModel
import com.example.roombooking.data.StudentsRepository

/**
 * Provides Factory to create instance of ViewModel for the entire Student app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for StudentEditViewModel
        initializer {
            StudentEditViewModel(
                this.createSavedStateHandle(),
                studentApplication().container.studentsRepository
            )
        }
        // Initializer for StudentEntryViewModel
        initializer {
            StudentEntryViewModel(studentApplication().container.studentsRepository)
        }

        // Initializer for StudentDetailsViewModel
        initializer {
            StudentDetailsViewModel(
                this.createSavedStateHandle(),
                studentApplication().container.studentsRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(studentApplication().container.studentsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [StudentApplication].
 */
fun CreationExtras.studentApplication(): StudentApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StudentApplication)