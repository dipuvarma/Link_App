package com.example.linkapp.data.repo

import android.util.Log
import com.example.linkapp.domain.model.UserDetailsUi
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import com.example.linkapp.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseAuthRepoImpl(val auth: FirebaseAuth) : FirebaseAuthRepo {

    override suspend fun createAccount(email: String, password: String): Response<FirebaseUser> {
        return withContext(Dispatchers.IO) {
            try {
                val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                val user = authResult.user
                if (user != null) {
                    Response.Success(user)
                } else {
                    Response.Error("User creation failed: No user found")
                }
            } catch (e: FirebaseAuthUserCollisionException) {
                Response.Error(e.message ?: "Email already in use")
            } catch (e: Exception) {
                Response.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    override suspend fun loginAccount(email: String, password: String) {
        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "loginAccount: Success")
                } else {
                    Log.d("TAG", "loginAccount: Failed")
                }
            }
        }
    }

    override suspend fun logoutAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun forgetAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }


}