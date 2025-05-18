package com.example.linkapp.presentation.features.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistrationViewModel : ViewModel() {

    private val _registrationUiState = MutableStateFlow(RegistrationState())
    val registrationUiState = _registrationUiState.asStateFlow()


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

    fun repeatPassword(repeatPassword: String) {
        _registrationUiState.value = registrationUiState.value.copy(
            repeatPassword = repeatPassword
        )
    }

    fun isHidePassword() {
        _registrationUiState.value = registrationUiState.value.copy(
            isHidePassword = !registrationUiState.value.isHidePassword
        )
    }

    fun isHideRepeatPassword() {
        _registrationUiState.value = registrationUiState.value.copy(
            isHideRepeatPassword = !registrationUiState.value.isHideRepeatPassword
        )
    }


}