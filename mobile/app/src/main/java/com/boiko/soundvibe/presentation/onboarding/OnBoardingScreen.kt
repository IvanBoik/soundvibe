package com.boiko.soundvibe.presentation.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import androidx.compose.foundation.background
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
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boiko.soundvibe.R
import com.boiko.soundvibe.presentation.onboarding.components.ThirdServiceButton
import com.boiko.soundvibe.ui.theme.Montserrat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(
    navigate: (String) -> Unit,
    event: (OnBoardingEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.ic_onboarding_background),
                contentScale = ContentScale.FillBounds
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
                onClick = { showBottomSheet = true }
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
                    .padding(bottom = 36.dp)
                    .height(50.dp)
                    .fillMaxWidth(0.9f)
                    .background(getGradient(), shape = ButtonDefaults.shape),
                onClick = { /*TODO*/ }
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

            if (showBottomSheet) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false },
                    containerColor = MaterialTheme.colorScheme.primary,
                    dragHandle = { BottomSheetDefaults.DragHandle(color = Color.Transparent)},
                    modifier = Modifier.fillMaxHeight(0.85f)
                ) {
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
                                modifier = Modifier.padding(start = 20.dp)
                                    .clickable {
                                        scope.launch {
                                            sheetState.hide()
                                            showBottomSheet = false
                                        }
                                    }
                            )
                            Text(
                                text = "Sign up",
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
                            text = "Sign up with",
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
                    }
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
        OnBoardingScreen(
            navigate = {},
            event = {}
        )
    }
}