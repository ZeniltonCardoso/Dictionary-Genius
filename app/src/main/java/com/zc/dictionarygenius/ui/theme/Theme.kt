package com.zc.dictionarygenius.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Theme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // navigation bar
        systemUiController.setStatusBarColor(
            color = Color.DarkGray
        )
        // status bar
        systemUiController.setNavigationBarColor(
            color = Color.DarkGray
        )
    }
    MaterialTheme(colors = ColorPalette, typography = Typography, content = content)
}

@Composable
fun DialogThemeOverlay(content: @Composable () -> Unit) {
    val dialogColors = darkColors(
        primary = Color.White,
        surface = Color.White.copy(alpha = 0.12f).compositeOver(Color.Black),
        onSurface = Color.White
    )

    val currentTypography = MaterialTheme.typography
    val dialogTypography = currentTypography.copy(
        body2 = currentTypography.body1.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 1.sp
        ),
        button = currentTypography.button.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.2.em
        )
    )
    MaterialTheme(colors = dialogColors, typography = dialogTypography, content = content)
}
