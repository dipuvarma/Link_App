package com.example.linkapp.presentation.screens.auth

import com.example.linkapp.domain.model.UserDetailsUi

data class AuthUiState(
    val userDetailsUi: UserDetailsUi = UserDetailsUi(),
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordErrors: List<String> = emptyList(),
    val showPasswordErrors: Boolean = false,
    val showPasswordStrength: Boolean = false,
    val passwordStrength: PasswordStrength = PasswordStrength.WEAK
)
