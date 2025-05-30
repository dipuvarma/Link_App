package com.example.linkapp.domain.di

import com.example.linkapp.data.repo.FirebaseAuthRepoImpl
import com.example.linkapp.domain.repo.FirebaseAuthRepo
import com.example.linkapp.presentation.screens.auth.login.LoginViewModel
import com.example.linkapp.presentation.screens.auth.registration.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
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
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideFirebaseDatabase(auth: FirebaseAuth): FirebaseAuthRepo {
        return FirebaseAuthRepoImpl(auth)
    }

    @Provides
    @Singleton
    fun provideRepo(firebaseAuthRepo: FirebaseAuthRepo) = RegistrationViewModel(firebaseAuthRepo)

    @Provides
    @Singleton
    fun provideLoginRepo(firebaseAuthRepo: FirebaseAuthRepo) = LoginViewModel(firebaseAuthRepo)

}