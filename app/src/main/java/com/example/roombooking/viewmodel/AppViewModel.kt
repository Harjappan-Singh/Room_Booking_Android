package com.example.roombooking.viewmodel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _currentScreen = MutableStateFlow("Splash")
    val currentScreen: StateFlow<String> get() = _currentScreen

    private val _isAppLoading = MutableStateFlow(true)
    val isAppLoading: StateFlow<Boolean> get() = _isAppLoading

    init {
        initializeApp()
    }

    private fun initializeApp() {
        viewModelScope.launch {
            delay(3000)
            _isAppLoading.value = false
            _currentScreen.value = "LoginRegister"
        }
    }

    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }
}
