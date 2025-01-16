package org.denis.expenseapp.presentation.platform

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getStatusBarHeightDp(): Float {
    return  (UIApplication.sharedApplication.statusBarFrame.size+16).toFloat()
}

@Composable
actual fun getNavBarHeightDp(): Float {
    return 0f
}