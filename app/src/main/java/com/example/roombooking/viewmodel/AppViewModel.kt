package com.example.roombooking.viewmodel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AppViewModel : ViewModel() {
    private val _currentScreen = MutableStateFlow("Login")
    val currentScreen: StateFlow<String> get() = _currentScreen

    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }
}
