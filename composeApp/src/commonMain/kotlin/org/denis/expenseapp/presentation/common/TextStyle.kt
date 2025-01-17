package org.denis.expenseapp.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleTextBold(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun BodyTextLarge(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun BodyTextMedium(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun BodyTextSmall(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
        textAlign = textAlign,
    )
}