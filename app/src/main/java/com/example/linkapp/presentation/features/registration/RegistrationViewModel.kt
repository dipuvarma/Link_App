package com.example.linkapp.presentation.features.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkapp.data.model.UserDetails
import com.example.linkapp.domain.model.UserDetailsUi
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import com.example.linkapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
) : ViewModel() {

    private val _registrationUiState = MutableStateFlow(RegistrationState())
    val registrationUiState = _registrationUiState.asStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.UpdateEmail -> {
                _registrationUiState.update {
                    it.copy(userDetailsUi = it.userDetailsUi.copy(email = event.email))
                }
            }

            is RegistrationEvent.UpdateName -> {
                _registrationUiState.update {
                    it.copy(userDetailsUi = it.userDetailsUi.copy(name = event.name))
                }

            }

            is RegistrationEvent.UpdatePassword -> {
                _registrationUiState.update {
                    it.copy(userDetailsUi = it.userDetailsUi.copy(password = event.password))
                }
            }
        }
    }

    fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            _registrationUiState.update { it.copy(isLoading = true) }
            val response = firebaseAuthRepo.createAccount(email, password)
            _registrationUiState.update { it.copy(isLoading = false) }
            when (response) {
                is Response.Error -> _registrationUiState.update {
                    it.copy(
                        error = response.error
                    )
                }

                is Response.Success<*> -> {
                    _registrationUiState.update { it.copy(success = response.data.toString()) }
                }
            }
        }
    }

    fun togglePasswordVisibility() {
        _registrationUiState.update { it.copy(isHidePassword = !it.isHidePassword) }
    }

}