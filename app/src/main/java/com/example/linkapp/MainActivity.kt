package com.example.linkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.linkapp.presentation.features.login.LoginViewModel
import com.example.linkapp.presentation.features.registration.RegistrationScreen
import com.example.linkapp.presentation.features.registration.RegistrationViewModel
import com.example.linkapp.presentation.navigation.LinkAppGraph
import com.example.linkapp.ui.theme.LinkAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: RegistrationViewModel by viewModels()
        val loginViewModel: LoginViewModel by viewModels()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinkAppTheme(darkTheme = false, dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LinkAppGraph(
                        loginViewModel = loginViewModel,
                        viewModel
                    )
                }
            }
        }
    }
}