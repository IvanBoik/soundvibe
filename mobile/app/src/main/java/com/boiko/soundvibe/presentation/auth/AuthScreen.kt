package com.boiko.soundvibe.presentation.auth

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.boiko.soundvibe.R
import com.boiko.soundvibe.auth.AuthResult
import com.boiko.soundvibe.presentation.auth.components.SignInBottomSheet
import com.boiko.soundvibe.presentation.auth.components.SignUpBottomSheet
import com.boiko.soundvibe.presentation.navigation.Routes.HOME_SCREEN
import com.boiko.soundvibe.ui.theme.Montserrat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navigate: (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val scaffoldState = rememberBottomSheetScaffoldState(sheetState)
    val scope = rememberCoroutineScope()
    var isClickOnSignUp by remember { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect {result ->
            when(result) {
                is AuthResult.Authorized -> {
                    sheetState.hide()
                    navigate(HOME_SCREEN)
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(context, "You're not authorized", Toast.LENGTH_LONG).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(context, "An unknown error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        modifier = Modifier.fillMaxSize(),
        sheetDragHandle = {BottomSheetDefaults.DragHandle(color = Color.Transparent)},
        sheetContainerColor = MaterialTheme.colorScheme.primary,
        sheetContent = {
            if (isClickOnSignUp) {
                SignUpBottomSheet(
                    sheetState = sheetState,
                    scope = scope,
                    navigate = navigate,
                    viewModel = viewModel
                )
            }
            else {
                SignInBottomSheet(
                    sheetState = sheetState,
                    scope = scope,
                    navigate = navigate,
                    viewModel = viewModel
                )
            }
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.ic_onboarding_background),
                    contentScale = ContentScale.FillBounds,
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "FIND MUSIC",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.White,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )
                Text(
                    text = "FIND YOUR VIBE",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.White,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Button(
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(50.dp)
                        .fillMaxWidth(0.9f),
                    onClick = {
                        isClickOnSignUp = true
                        scope.launch { sheetState.expand() }
                    }
                ) {
                    Text(
                        text = "Sign up",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Button(
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(bottom = 36.dp)
                        .height(50.dp)
                        .fillMaxWidth(0.9f)
                        .background(getGradient(), shape = ButtonDefaults.shape),
                    onClick = {
                        isClickOnSignUp = false
                        scope.launch { sheetState.expand() }
                    }
                ) {
                    Text(
                        text = "Log in",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

private fun getGradient(): Brush {
    val color1 = Color(0xFFA52D85)
    val color2 = Color(0xFF3E0068)
    return Brush.horizontalGradient(listOf(color1, color2))
}

@Preview(uiMode = UI_MODE_NIGHT_MASK)
@Composable
private fun OnBoardingScreenPreview() {
    MaterialTheme {
        AuthScreen(
            navigate = {}
        )
    }
}