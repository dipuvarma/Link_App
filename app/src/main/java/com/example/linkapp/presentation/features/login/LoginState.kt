package com.example.linkapp.presentation.features.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isHidePassword: Boolean = false,
    val isHideRepeatPassword: Boolean = false,
)
