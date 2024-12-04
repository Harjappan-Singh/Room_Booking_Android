package com.example.roombooking

import android.app.Application
import com.example.roombooking.data.AppContainer
import com.example.roombooking.data.AppDataContainer

class StudentApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}