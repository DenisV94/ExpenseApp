package org.denis.expenseapp

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.denis.expenseapp.di.initKoin

@Composable
@Preview
fun App() {
    initKoin()
}