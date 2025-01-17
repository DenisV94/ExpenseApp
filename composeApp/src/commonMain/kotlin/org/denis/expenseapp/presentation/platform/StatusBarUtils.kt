package org.denis.expenseapp.presentation.platform

import androidx.compose.runtime.Composable

@Composable
expect fun getStatusBarHeightDp(): Float // Retrieve the top status bar height

@Composable
expect fun getNavBarHeightDp(): Float // Retrieve the bottom navigation bar height