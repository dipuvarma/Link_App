package com.example.linkapp.presentation.features.registration

data class RegistrationState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isHidePassword: Boolean = false,
    val isHideRepeatPassword: Boolean = false,
)
