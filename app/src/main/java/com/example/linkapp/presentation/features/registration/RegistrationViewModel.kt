package com.example.linkapp.presentation.features.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel : ViewModel() {

    private val _registrationUiState = MutableStateFlow(RegistrationState())
    val registrationUiState = _registrationUiState.asStateFlow()

    fun updateName(name: String) {
        _registrationUiState.update { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _registrationUiState.value = registrationUiState.value.copy(
            email = email
        )
    }

    fun updatePassword(password: String) {
        _registrationUiState.value = registrationUiState.value.copy(
            password = password
        )
    }
    fun togglePasswordVisibility() {
        _registrationUiState.update { it.copy(isHidePassword = !it.isHidePassword) }
    }


}