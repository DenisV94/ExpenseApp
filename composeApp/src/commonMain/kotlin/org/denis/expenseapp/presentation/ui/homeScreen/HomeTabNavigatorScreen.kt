package org.denis.expenseapp.presentation.ui.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab.HomeTab

@Composable
fun HomeTabNavigatorScreen() {
    var isVisible by remember { mutableStateOf(true) } // Visibility state for the bottom navigation bar

    // Initialize the HomeTab with the visibility callback
    val homeTab = remember {
        HomeTab(onNavigator = { isVisible = it })
    }

    // Use TabNavigator to manage tab navigation
    TabNavigator(tab = homeTab) {
        val tabNavigator = LocalTabNavigator.current

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                // Animate visibility of the bottom navigation bar
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    BottomNavigation {
                        TabNavigationItem(homeTab)
                    }
                }
            },
            content = {
                // Display the currently selected tab's content
                CurrentTab()
            }
        )
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab }, // Switch to the selected tab
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = tab.options.title
                )
            }
        },
        label = {
            Text(text = tab.options.title)
        }
    )
}