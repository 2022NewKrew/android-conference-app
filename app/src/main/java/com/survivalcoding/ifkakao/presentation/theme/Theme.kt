package com.survivalcoding.ifkakao.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
    primary = Color.Black,
    primaryVariant = DarkGrey,
    onPrimary = Color.White,
    secondary = LightYellow,
    secondaryVariant = StrongYellow,
    background = Color.Black,
    onBackground = Color.White,
    surface = LightGrey
)

@Composable
fun IfKakaoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColors,
        typography = IfKakaoTypography,
        content = content
    )
}