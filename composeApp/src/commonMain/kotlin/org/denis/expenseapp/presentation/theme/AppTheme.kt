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
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.montserrat_medium
import org.jetbrains.compose.resources.Font

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val darkColorScheme =
        darkColorScheme(
            primary = Color(0xFF3B82F6),
            onPrimary = Color(0xFFFFFFFF),
            background = Color(0xFFF5F7FA),
            onBackground = Color(0xFF000000),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000)
        )

    val lightColorScheme =
        lightColorScheme(
            primary = Color(0xFF3B82F6),
            onPrimary = Color(0xFFFFFFFF),
            background = Color(0xFFF5F7FA),
            onBackground = Color(0xFF000000),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000)
        )

    val fontFamilyMontserratMedium = FontFamily(Font(Res.font.montserrat_medium))

    val typography = Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight(700),
            fontSize = 18.sp,
            color = Color.Black,
            fontFamily = fontFamilyMontserratMedium
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            color = Color.Black,
            fontFamily = fontFamilyMontserratMedium
        )
    )

    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme else lightColorScheme,
        typography = typography,
        content = content
    )
}