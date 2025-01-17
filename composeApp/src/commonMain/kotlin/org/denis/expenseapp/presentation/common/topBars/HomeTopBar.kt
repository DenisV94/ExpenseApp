package org.denis.expenseapp.presentation.common.topBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.denis.expenseapp.presentation.platform.getStatusBarHeightDp
import org.denis.expenseapp.presentation.theme.BodyTextBold
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
@Composable
fun TopBarHome(
    title: String
) {
    Box(
        modifier = Modifier
            .height(48.dp + getStatusBarHeightDp().dp) // Adjust the height by platform
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyTextBold(text = title)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}