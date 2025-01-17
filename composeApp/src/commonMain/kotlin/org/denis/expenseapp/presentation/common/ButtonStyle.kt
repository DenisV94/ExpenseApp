package org.denis.expenseapp.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ButtonPrimary(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    text: String,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        contentPadding = PaddingValues(8.dp),
        shape = shape,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}