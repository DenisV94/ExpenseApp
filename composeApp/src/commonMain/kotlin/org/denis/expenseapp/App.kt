package org.denis.expenseapp

import androidx.compose.runtime.Composable
import org.denis.expenseapp.di.initKoin
import org.denis.expenseapp.presentation.ui.UILauncher
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainApp() {
    initKoin()
    UILauncher()
}