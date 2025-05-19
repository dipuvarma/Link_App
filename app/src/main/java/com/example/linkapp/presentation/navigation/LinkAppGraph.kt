package com.example.linkapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linkapp.presentation.features.home.HomeScreen
import com.example.linkapp.presentation.features.login.LoginScreen
import com.example.linkapp.presentation.features.login.LoginViewModel
import com.example.linkapp.presentation.features.registration.RegistrationScreen
import com.example.linkapp.presentation.features.registration.RegistrationViewModel

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
        composable<Home> {
            HomeScreen()
        }
    }

}