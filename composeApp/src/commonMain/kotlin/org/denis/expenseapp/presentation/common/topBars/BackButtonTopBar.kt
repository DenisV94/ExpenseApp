package org.denis.expenseapp.presentation.common.topBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.navigator.Navigator
import org.denis.expenseapp.presentation.platform.getStatusBarHeightDp
import org.denis.expenseapp.presentation.theme.BodyTextBold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.ic_arrow_back


@Composable
fun BackButtonTopBar(
    title: String,
    navigator: Navigator
) {

    Box(
        modifier = Modifier
            .height(52.dp + getStatusBarHeightDp().dp) // Adjust the height by platform
            //.background(color = MaterialTheme.colorScheme.background)
            .background(Color.Gray)
    ) {

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BodyTextBold(text = title)
            Spacer(modifier = Modifier.weight(1f))


            IconButton(
                onClick = { navigator.pop() },
                modifier = Modifier
                    .zIndex(1f)
                    .size(28.dp)
                    .offset(
                        x = (-6).dp
                    )
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}