package com.example.linkapp.presentation.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.linkapp.presentation.component.FormLabel
import com.example.linkapp.presentation.features.home.HomeScreen
import com.example.linkapp.presentation.navigation.Home
import com.example.linkapp.presentation.navigation.Registration


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController,
) {
    val stateUi by viewModel.loginUiState.collectAsState()

    val state = stateUi.userDetailsUi

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val repeatPasswordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Header
            Text(
                text = "Login account",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Please enter your email or password",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Email
            FormLabel("Your email")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::updateEmail,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailFocusRequester),
                placeholder = { Text("Enter Your Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email icon")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password
            FormLabel("Password")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = viewModel::updatePassword,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                placeholder = { Text("Enter Your Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password icon")
                },
                trailingIcon = {
                    if (state.password.isNotEmpty()) {
                        IconButton(onClick = viewModel::togglePasswordVisibility) {
                            Icon(
                                imageVector = if (stateUi.isHidePassword)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.RemoveRedEye,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                },
                visualTransformation = if (stateUi.isHidePassword) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { repeatPasswordFocusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Repeat Password
            FormLabel("Repeat Password")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = stateUi.repeatPassword,
                onValueChange = viewModel::repeatPassword,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(repeatPasswordFocusRequester),
                placeholder = { Text("Repeat Password") },
                leadingIcon = {
                    Icon(Icons.Default.Password, contentDescription = "Repeat password icon")
                },
                trailingIcon = {
                    if (stateUi.repeatPassword.isNotEmpty()) {
                        IconButton(onClick = viewModel::toggleRepeatPasswordVisibility) {
                            Icon(
                                imageVector = if (stateUi.isHideRepeatPassword)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.RemoveRedEye,
                                contentDescription = "Toggle repeat password visibility"
                            )
                        }
                    }
                },
                visualTransformation = if (stateUi.isHideRepeatPassword) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Register Button
            Button(
                onClick = {
                    navController.navigate(Home)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Login Prompt
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Don't have account?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    navController.navigate(Registration)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Register")
            }
        }
    }
}