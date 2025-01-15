package org.denis.expenseapp.presentation.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.denis.expenseapp.presentation.common.topBars.TopBarHome
import org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab.HomeTab

internal class HomeTabNavigatorScreen: Screen {
    @Composable
    override fun Content() {
        // Initialize the list of tabs
        val tabs = listOf(HomeTab)

        // Wrap with TabNavigator to initialize it with a default tab
        TabNavigator(tab = HomeTab) {
            // Observe the current tab from the TabNavigator
            val currentTab = LocalTabNavigator.current.current

            Scaffold(
                content = {
                    // Adjust padding based on whether the NavigationBar is visible
                    Column(
                        modifier = Modifier.padding(
                            bottom = if (currentTab in tabs) 56.dp else 0.dp
                        )
                    ) {
                        CurrentTab() // Render the content of the current tab
                    }
                },
                bottomBar = {
                    // Conditionally show the NavigationBar based on the current tab
                    if (currentTab in tabs) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.background
                        ) {
                            tabs.forEach { tab ->
                                TabNavigationItem(tab)
                            }
                        }
                    }
                }
            )
        }

    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        modifier = Modifier.background(Color.Gray),
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab }, // Update the tab
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        alwaysShowLabel = false
    )
}