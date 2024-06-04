package com.boiko.soundvibe.presentation.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boiko.soundvibe.R
import com.boiko.soundvibe.presentation.auth.AuthUiEvent
import com.boiko.soundvibe.presentation.auth.AuthViewModel
import com.boiko.soundvibe.ui.theme.Montserrat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    viewModel: AuthViewModel
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxHeight(0.85f)) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                            scope.launch {
                                focusManager.clearFocus()
                                sheetState.hide()
                            }
                        }
                )
                Text(
                    text = "Log in",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.Transparent,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Log in with",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ThirdServiceButton(icon = R.drawable.vk) {

                }
                ThirdServiceButton(icon = R.drawable.yandex) {

                }
                ThirdServiceButton(icon = R.drawable.google) {

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            TextInput(value = email, placeholder = "Email") {
                it.isNotEmpty()
            }
            TextInput(
                value = password,
                placeholder = "Password",
                visualTransformation = PasswordVisualTransformation()
            ) {
                it.isNotEmpty()
            }
            Spacer(modifier = Modifier.height(44.dp))
            Button(
                onClick = {
                    viewModel.onEvent(AuthUiEvent.SignIn(
                        email = email.value,
                        password = password.value
                    ))
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(46.dp)
            ) {
                Text(
                    text = "Log in",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}