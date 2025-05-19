package com.example.linkapp.data.repo

import android.util.Log
import com.example.linkapp.data.mapper.toUserDetails
import com.example.linkapp.domain.model.UserDetailsUi
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepoImpl (firebaseDatabase: FirebaseDatabase) : FirebaseAuthRepo {

    val myRef = firebaseDatabase.getReference("Users")


    override suspend fun createAccount(userDetailsUi: UserDetailsUi) {
        val userDetails = userDetailsUi.toUserDetails()
        try {
            myRef.child(userDetails.id!!).setValue(userDetails).await()
        } catch (e: Exception) {
            Log.e("TAG", "createAccount: $e")
        }

    }

    override suspend fun loginAccount(userDetailsUi: UserDetailsUi) {
        TODO("Not yet implemented")
    }

    override suspend fun logoutAccount(userDetailsUi: UserDetailsUi) {
        TODO("Not yet implemented")
    }

}