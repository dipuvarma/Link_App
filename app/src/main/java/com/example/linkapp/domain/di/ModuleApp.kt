package com.example.linkapp.domain.di

import com.example.linkapp.data.remote.firebase.FirebaseAuth
import com.example.linkapp.data.repo.FirebaseAuthRepoImpl
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import com.example.linkapp.presentation.features.registration.RegistrationViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Provides
    @Singleton
    fun provideRealtimeDatabase() = FirebaseDatabase.getInstance()


    @Provides
    @Singleton
    fun provideFirebaseDatabase(firebaseDatabase: FirebaseDatabase): FirebaseAuthRepo {
        return FirebaseAuthRepoImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideRepo(firebaseAuthRepo: FirebaseAuthRepo) = RegistrationViewModel(firebaseAuthRepo)


}