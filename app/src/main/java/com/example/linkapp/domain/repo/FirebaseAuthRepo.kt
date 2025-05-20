package com.example.linkapp.domain.repo

import com.example.linkapp.data.model.UserDetails
import com.example.linkapp.domain.model.UserDetailsUi
import com.example.linkapp.utils.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepo {
    suspend fun createAccount( email: String, password: String ): Response<FirebaseUser>
    suspend fun loginAccount(email: String, password: String )
    suspend fun logoutAccount(userDetailsUi: UserDetailsUi)
}