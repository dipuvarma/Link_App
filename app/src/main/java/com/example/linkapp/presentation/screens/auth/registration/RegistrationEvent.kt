package com.example.linkapp.presentation.screens.auth.registration

sealed class RegistrationEvent {
    data class UpdateName(val name: String) : RegistrationEvent()
    data class UpdateEmail(val email: String) : RegistrationEvent()
    data class UpdatePassword(val password: String) : RegistrationEvent()
}