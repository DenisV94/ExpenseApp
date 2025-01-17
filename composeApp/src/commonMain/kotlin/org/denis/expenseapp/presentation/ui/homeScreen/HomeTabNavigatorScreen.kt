package org.denis.expenseapp.presentation.ui.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.ic_plus
import org.denis.expenseapp.presentation.platform.getNavBarHeightDp
import org.denis.expenseapp.presentation.ui.addScreen.AddScreen
import org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab.HomeTab
import org.jetbrains.compose.resources.painterResource

class HomeTabNavigatorScreen : Screen {

    @Composable
    override fun Content() {
        var isVisible by remember { mutableStateOf(true) } // Visibility state for the bottom navigation bar
        val navigator = LocalNavigator.currentOrThrow
        val homeTab = remember {
            HomeTab(onNavigator = { isVisible = it })
        }

        // Root Navigator for global screen navigation
        TabNavigator(tab = homeTab) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInVertically { it },
                        exit = slideOutVertically { it }
                    ) {
                        CustomNavigationBar(
                            homeTab = homeTab,
                            onNavigate = { navigator.push(AddScreen()) }
                        )
                    }
                },
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 65.dp - getNavBarHeightDp().dp)
                    ) {
                        CurrentTab()
                    }
                }
            )
        }
    }
}

@Composable
fun CustomNavigationBar(
    homeTab: Tab,
    onNavigate: () -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(64.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        // Tab navigation items for HomeTab
        TabNavigationItem(homeTab)

        // Custom navigation item for navigating to a new screen from navbar
        CustomNavigationItem(onNavigate = onNavigate)
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = true,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = tab.options.title
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.surface,
            indicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun RowScope.CustomNavigationItem(onNavigate: () -> Unit) {
    NavigationBarItem(
        selected = false,
        onClick = { onNavigate() },
        icon = {
            Icon(
                painterResource(Res.drawable.ic_plus),
                contentDescription = "New Screen"
            ) // Custom icon
        }
    )
}