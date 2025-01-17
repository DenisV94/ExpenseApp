package org.denis.expenseapp.presentation.ui.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.app_name
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.denis.expenseapp.presentation.common.TitleTextBold
import org.jetbrains.compose.resources.stringResource

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val splashScreenDuration = 1500L // 3 seconds
    val scope = rememberCoroutineScope()

    SplashScreenScreen()

    scope.launch {
        delay(splashScreenDuration)
        onTimeout()
    }
}

@Composable
private fun SplashScreenScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleTextBold(
                text = stringResource(Res.string.app_name)
            )
        }
    }
}