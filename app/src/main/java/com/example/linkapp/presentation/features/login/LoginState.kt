package com.example.linkapp.presentation.features.login

import com.example.linkapp.domain.model.UserDetailsUi

data class LoginState(
    val userDetailsUi: UserDetailsUi = UserDetailsUi(),
    val repeatPassword: String = "",
    val isHidePassword: Boolean = false,
    val isHideRepeatPassword: Boolean = false,
    val error: String = "",
    val isLoading: Boolean = false,
)
