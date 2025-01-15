package org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.common.topBars.TopBarHome
import org.denis.expenseapp.presentation.theme.ButtonPrimary
import org.denis.expenseapp.presentation.theme.MainBodyStyle
import org.denis.expenseapp.presentation.ui.addScreen.AddScreen


internal class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tabNavigator = LocalTabNavigator.current


        Scaffold(
            topBar = {
                TopBarHome("Home")
            },
            content = {
                HomeScreenBody(
                    onClick = {
                        navigator.push(AddScreen())
                    }
                )
            }
        )

    }

    @Composable
    fun HomeScreenBody(
        onClick: () -> Unit,
    ) {
        MainBodyStyle(
            backgroundColor = Color.Red,
            content = {
                Spacer(modifier = Modifier.height(150.dp))
                Column {
                    ButtonPrimary(
                        text = "Add Expense",
                        onClick = {
                            onClick()
                        }
                    )
                }

            }
        )
    }
}