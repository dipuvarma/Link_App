package com.example.linkapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linkapp.presentation.screens.auth.login.LoginScreen
import com.example.linkapp.presentation.screens.auth.registration.RegistrationScreen

@Composable
fun LinkAppGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
        composable<Registration> {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
    }

}