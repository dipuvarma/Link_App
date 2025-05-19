package com.example.linkapp.presentation.features.login

import androidx.lifecycle.ViewModel
import com.example.linkapp.domain.model.UserDetailsUi
import com.example.linkapp.presentation.features.registration.RegistrationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel (): ViewModel(){

    private val _loginUiState = MutableStateFlow(LoginState())
    val loginUiState = _loginUiState.asStateFlow()


    fun updateEmail(email: String) {
        _loginUiState.update {
            it.copy(userDetailsUi = UserDetailsUi(email = email))
        }
    }

    fun updatePassword(password: String) {
        _loginUiState.update {
            it.copy(
                userDetailsUi = UserDetailsUi(password = password)
            )
        }
    }

    fun repeatPassword(repeatPassword: String) {
        _loginUiState.update {
            it.copy(
                repeatPassword = repeatPassword
            )
        }
    }

    fun togglePasswordVisibility() {
        _loginUiState.update { it.copy(isHidePassword = !it.isHidePassword) }
    }

    fun toggleRepeatPasswordVisibility() {
        _loginUiState.update { it.copy(isHideRepeatPassword = !it.isHideRepeatPassword) }
    }

}