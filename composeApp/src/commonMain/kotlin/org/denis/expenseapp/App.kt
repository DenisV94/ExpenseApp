package org.denis.expenseapp

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.denis.expenseapp.di.initKoin
import org.denis.expenseapp.presentation.ui.UILauncher

@Composable
@Preview
fun App() {
    initKoin()
    UILauncher()
}