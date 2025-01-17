package org.denis.expenseapp.presentation.platform

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getStatusBarHeightDp(): Float {
    return  0f
}

@Composable
actual fun getNavBarHeightDp(): Float {
    return 0f
}