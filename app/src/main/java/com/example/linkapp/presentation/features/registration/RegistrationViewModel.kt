package com.example.linkapp.presentation.features.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkapp.data.model.UserDetails
import com.example.linkapp.data.remote.firebase.FirebaseAuth
import com.example.linkapp.domain.model.UserDetailsUi
import com.example.linkapp.domain.repo.FirebaseAuthRepo
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


    fun createAccount(userDetailsUi: UserDetailsUi) {
        viewModelScope.launch {
            firebaseAuthRepo.createAccount(userDetailsUi)
        }
    }

    fun updateName(name: String) {
        _registrationUiState.update {
            it.copy(userDetailsUi = it.userDetailsUi.copy(name =name)) }
    }

    fun updateEmail(email: String) {
        _registrationUiState.update {
            it.copy(userDetailsUi = it.userDetailsUi.copy(email =email))
        }
    }

    fun updatePassword(password: String) {
        _registrationUiState.update {
            it.copy(userDetailsUi = it.userDetailsUi.copy(password =password))
        }
    }

    fun togglePasswordVisibility() {
        _registrationUiState.update { it.copy(isHidePassword = !it.isHidePassword) }
    }

}