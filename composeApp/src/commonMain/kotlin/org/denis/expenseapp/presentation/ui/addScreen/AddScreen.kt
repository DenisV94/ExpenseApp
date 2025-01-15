package org.denis.expenseapp.presentation.ui.addScreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.theme.MainBodyStyle


class AddScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                BackButtonTopBar(title = "Add Expense", navigator = navigator)
            },
            content = {
                AddScreenBody()
            }
        )
    }

    @Composable
    fun AddScreenBody() {
        MainBodyStyle(
            content = {

            }
        )
    }
}