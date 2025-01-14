package org.denis.expenseapp.presentation.ui.homeScreen

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab.HomeTab
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import org.denis.expenseapp.presentation.common.topBars.TopBarHome

class HomeTabNavigatorScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab)
                )
            }
        ) { tabNavigator ->
            Scaffold(
                topBar = {
                    TopBarHome(
                        title = "Home"
                    )
                },
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                    {
                        TabNavigationItem(HomeTab)
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = {
                Icon(
                    painter = tab.options.icon!!,
                    contentDescription = tab.options.title
                )
            },
            label = {
                androidx.compose.material3.Text(tab.options.title)
            },
            alwaysShowLabel = true
        )
    }
}