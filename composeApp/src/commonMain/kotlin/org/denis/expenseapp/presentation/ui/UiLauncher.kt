package org.denis.expenseapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import org.denis.expenseapp.presentation.theme.AppTheme
import org.denis.expenseapp.presentation.ui.homeScreen.HomeTabNavigatorScreen
import org.denis.expenseapp.presentation.ui.splashScreen.SplashScreen

@Composable
fun UILauncher() {

    AppTheme {
        var showSplashScreen by remember { mutableStateOf(true) }

        if (showSplashScreen) {
            SplashScreen(onTimeout = { showSplashScreen = false })
        } else {
           HomeTabNavigatorScreen()
        }
    }
}