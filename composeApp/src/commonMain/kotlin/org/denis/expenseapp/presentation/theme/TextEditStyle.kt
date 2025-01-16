package org.denis.expenseapp.presentation.theme

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi


    @Composable
    fun BasicTextEditField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,
        modifier: Modifier = Modifier,
        leadingIcon: @Composable (() -> Unit)? = null,
        shape: Shape = MaterialTheme.shapes.medium,
        backgroundColors: TextFieldColors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        onImeAction: () -> Unit,
        fontColor: Color = MaterialTheme.colors.onSurface,
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
                    style = MaterialTheme.typography.body1.copy(fontSize = 16.sp)
                )
            },
            leadingIcon = leadingIcon,
            textStyle = MaterialTheme.typography.body1.copy(
                color = fontColor,
                fontSize = 16.sp
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                onImeAction()
            }
            ), // Handle the action

        )
    }
