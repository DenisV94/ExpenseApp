package org.denis.expenseapp.presentation.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsCompat

@Composable
actual fun getStatusBarHeightDp(): Float {
    val view = LocalView.current
    val windowInsets = WindowInsetsCompat.toWindowInsetsCompat(view.rootWindowInsets)
    val statusBarHeightPx = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
    val density = LocalDensity.current.density
    return statusBarHeightPx / density
}