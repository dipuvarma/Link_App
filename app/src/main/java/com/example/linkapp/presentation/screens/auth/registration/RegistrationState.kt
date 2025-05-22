package com.example.linkapp.presentation.screens.auth.registration

import com.example.linkapp.domain.model.UserDetailsUi

data class RegistrationState(
    val isLoading: Boolean = false,
    val userDetailsUi: UserDetailsUi = UserDetailsUi(),
    val error: String = "",
)

