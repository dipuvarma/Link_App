package com.example.linkapp.presentation.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginState())
    val loginUiState = _loginUiState.asStateFlow()


    fun loginAccount(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuthRepo.loginAccount(email, password)
        }
    }

    fun updateEmail(email: String) {
        _loginUiState.update {
            it.copy(userDetailsUi = it.userDetailsUi.copy(email = email))
        }
    }

    fun updatePassword(password: String) {
        _loginUiState.update {
            it.copy(
                userDetailsUi = it.userDetailsUi.copy(password = password)
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