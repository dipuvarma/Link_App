package com.example.linkapp.presentation.features.registration

import com.example.linkapp.domain.model.UserDetailsUi

data class RegistrationState(
    val userDetailsUi: UserDetailsUi = UserDetailsUi(),
    val isHidePassword: Boolean = false,
)

