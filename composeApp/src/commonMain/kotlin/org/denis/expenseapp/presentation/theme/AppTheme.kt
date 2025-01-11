package org.denis.expenseapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val darkColorScheme =
        darkColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6),
            background = Color(0xFFF1F1E6)
        )

    val lightColorScheme =
        lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6),
            background = Color(0xFFF1F1E6),
        )

    val fontFamilyMontserratMedium: FontFamily = TODO()
    val fontFamilyMontserratBold: FontFamily = TODO()

    val typography = Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight(700),
            fontSize = 18.sp,
            color = Color(0xFF003366),
            fontFamily = fontFamilyMontserratBold
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            color = Color(0xFF003366),
            fontFamily = fontFamilyMontserratMedium
        ),
        labelLarge = TextStyle(
            fontSize = 14.sp,
            fontFamily = fontFamilyMontserratMedium,
            fontWeight = FontWeight(500),
            color = Color(0xFF003366),
        ),
        labelSmall = TextStyle(
            fontSize = 10.sp,
            fontFamily = fontFamilyMontserratMedium,
            fontWeight = FontWeight(300),
            color = Color(0xFF012141),
        ),
    )

    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme else lightColorScheme,
        typography = typography,
        content = content
    )
}