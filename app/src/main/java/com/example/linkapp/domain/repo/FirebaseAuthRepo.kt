package com.example.linkapp.domain.repo

import com.example.linkapp.domain.model.UserDetailsUi

interface FirebaseAuthRepo {
    suspend fun createAccount( userDetailsUi: UserDetailsUi )
    suspend fun loginAccount(userDetailsUi: UserDetailsUi )
    suspend fun logoutAccount(userDetailsUi: UserDetailsUi)
}