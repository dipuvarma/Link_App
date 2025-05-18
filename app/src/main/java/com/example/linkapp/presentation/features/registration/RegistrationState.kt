package com.example.linkapp.presentation.features.registration

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isHidePassword: Boolean = false,
)
