package com.survivalcoding.ifkakao.compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


private val BlackKakaoPalette = darkColors(
    primary = Color.Black,
    primaryVariant = purple700,
    secondary = teal200,
)

@Composable
fun EvanTheme(
    content: @Composable() () -> Unit
) {
    val colors = BlackKakaoPalette

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = {
            ProvideTextStyle(
                value = TextStyle(color = Color.White),
                content = content
            )
        }
    )
}
