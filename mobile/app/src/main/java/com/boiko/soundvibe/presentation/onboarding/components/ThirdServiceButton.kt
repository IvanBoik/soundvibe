package com.boiko.soundvibe.presentation.onboarding.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.boiko.soundvibe.ui.theme.WhitePrimary

@Composable
fun ThirdServiceButton(
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    FilledIconButton(
        shape = CircleShape,
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .size(50.dp)
            .border(0.dp, Color.Transparent, CircleShape)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}