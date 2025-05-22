package com.example.linkapp.presentation.screens.auth.registration

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import com.example.linkapp.presentation.screens.auth.AuthUiState
import com.example.linkapp.presentation.screens.auth.getPasswordStrength
import com.example.linkapp.presentation.screens.auth.getValidPassword
import com.example.linkapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
) : ViewModel() {

    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()

    private val _registrationUiState = MutableStateFlow(RegistrationState())
    val registrationUiState = _registrationUiState.asStateFlow()

    fun onEvent(event: RegistrationEvent) {

        when (event) {
            is RegistrationEvent.UpdateEmail -> {
                val isValid = Patterns.EMAIL_ADDRESS.matcher(event.email).matches()
                _authUiState.update {
                    it.copy(
                        userDetailsUi = it.userDetailsUi.copy(
                            email = event.email,
                        ),
                        emailError = if (!isValid) "Invalid email format" else null
                    )
                }
            }

            is RegistrationEvent.UpdateName -> {
                _authUiState.update {
                    it.copy(
                        userDetailsUi = it.userDetailsUi.copy(name = event.name),
                        nameError = if (event.name.length < 2) "Name must be at least 2 characters" else if (event.name.length > 30) "Name must be max 30 characters" else null
                    )
                }

            }

            is RegistrationEvent.UpdatePassword -> {
                val errors = getValidPassword(event.password)
                val strength = getPasswordStrength(event.password)
                _authUiState.update {
                    it.copy(
                        userDetailsUi = it.userDetailsUi.copy(password = event.password),
                        passwordErrors = errors,
                        passwordStrength = strength,
                        showPasswordErrors = true,
                        showPasswordStrength = errors.isEmpty()
                    )
                }
            }
        }
    }

    fun togglePasswordVisibility() {
        _authUiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun isFormValid(): Boolean {
        val state = _authUiState.value
        return state.nameError == null &&
                state.emailError == null &&
                state.passwordErrors.isEmpty()&&
                state.userDetailsUi.password.isNotEmpty()
    }

    fun resetPasswordStrengthMessage() {
        _authUiState.update { it.copy(showPasswordStrength = false) }
    }


    fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            _registrationUiState.update { it.copy(isLoading = true) }
            firebaseAuthRepo.createAccount(email, password)
            _registrationUiState.update { it.copy(isLoading = false) }
        }
    }

}