package com.example.roombooking.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val studentsRepository: StudentsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineStudentsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [StudentsRepository]
     */
    override val studentsRepository: StudentsRepository by lazy {
        OfflineStudentsRepository(StudentDatabase.getDatabase(context).studentDao())
    }
}