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
import expenseapp.composeapp.generated.resources.montserrat_bold
import expenseapp.composeapp.generated.resources.montserrat_medium
import org.jetbrains.compose.resources.Font

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val darkColorScheme =
        darkColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6),
            background = Color(0xf1f2f4)
        )

    val lightColorScheme =
        lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6),
            background = Color(0xFFF1F1E6),
        )

    val fontFamilyMontserratMedium: FontFamily = FontFamily(Font(Res.font.montserrat_medium))
    val fontFamilyMontserratBold: FontFamily = FontFamily(Font(Res.font.montserrat_bold))

    val typography = Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight(700),
            fontSize = 18.sp,
            color = Color.Black,
            fontFamily = fontFamilyMontserratBold
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            color =Color.Black,
            fontFamily = fontFamilyMontserratMedium
        ),
        labelLarge = TextStyle(
            fontSize = 14.sp,
            fontFamily = fontFamilyMontserratMedium,
            fontWeight = FontWeight(500),
            color = Color.Black,
        ),
        labelSmall = TextStyle(
            fontSize = 10.sp,
            fontFamily = fontFamilyMontserratMedium,
            fontWeight = FontWeight(300),
            color = Color.Black,
        ),
    )

    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme else lightColorScheme,
        typography = typography,
        content = content
    )
}