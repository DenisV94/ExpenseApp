package org.denis.expenseapp.presentation.theme

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun TextEditField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColors: TextFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent
    ),
    onImeAction: () -> Unit,
    fontColor: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign = TextAlign.Start,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done // Set to Done or another appropriate action
    )
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value,
        colors = backgroundColors,
        shape = shape,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(
                text = label,
                textAlign = textAlign,
                color = fontColor.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
        },
        leadingIcon = leadingIcon,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = fontColor,
            fontSize = 16.sp
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onImeAction()
        }
        )

    )
}

@Composable
fun CurrencyInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColors: TextFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent
    ),
    onImeAction: () -> Unit,
    fontColor: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign = TextAlign.Start,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Number
    )
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value,
        colors = backgroundColors,
        shape = shape,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(
                text = label,
                textAlign = textAlign,
                color = fontColor.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
        },
        leadingIcon = leadingIcon,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = fontColor,
            fontSize = 16.sp
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onImeAction()
        }
        )

    )
}

