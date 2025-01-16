package org.denis.expenseapp.presentation.ui.addScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.add_screen_title
import kotlinx.datetime.LocalDate
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiAction
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiState
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


class AddScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<AddExpenseViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                BackButtonTopBar(
                    title = stringResource(Res.string.add_screen_title),
                    onBackButtonPressed = {navigator.pop()}
                )
                     },
            content = { paddingValues ->
                AddScreenBody(
                    uiState = viewModel.uiState.value,
                    onAction = viewModel::onAction,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        )
    }

    @Composable
    fun AddScreenBody(
        uiState: AddExpenseUiState,
        onAction: (AddExpenseUiAction) -> Unit,
        modifier: Modifier = Modifier
    ) {
        when (uiState) {
            is AddExpenseUiState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is AddExpenseUiState.Success -> {
                ExpenseForm(
                    description = uiState.description,
                    amount = uiState.amount,
                    category = uiState.category,
                    date = uiState.date,
                    onDescriptionChange = { onAction(AddExpenseUiAction.UpdateDescription(it)) },
                    onAmountChange = { onAction(AddExpenseUiAction.UpdateAmount(it)) },
                    onCategoryChange = { onAction(AddExpenseUiAction.SelectCategory(it)) },
                    onDateChange = { onAction(AddExpenseUiAction.SelectDate(it)) },
                    onSave = { onAction(AddExpenseUiAction.SaveExpense) }
                )
            }

            is AddExpenseUiState.Error -> {
                Box(modifier = modifier.fillMaxSize()) {
                    Text(
                        text = "Error loading data. Please try again.",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }

    @Composable
    fun ExpenseForm(
        description: String,
        amount: String,
        category: String,
        date: LocalDate,
        onDescriptionChange: (String) -> Unit,
        onAmountChange: (String) -> Unit,
        onCategoryChange: (String) -> Unit,
        onDateChange: (LocalDate) -> Unit,
        onSave: () -> Unit
    ) {
        val categories = listOf("Food", "Transport", "Entertainment", "Other")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Description Field
            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            // Amount Field
            OutlinedTextField(
                value = amount,
                onValueChange = onAmountChange,
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth()
            )

            // Category Selector
            DropdownMenuField(
                label = "Categoría",
                options = categories,
                selectedOption = category,
                onOptionSelected = onCategoryChange
            )

            // Date Selector
            DateSelector(
                label = "Fecha",
                selectedDate = date,
                onDateSelected = onDateChange
            )

            // Save Button
            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }

    @Composable
    fun DropdownMenuField(
        label: String,
        options: List<String>,
        selectedOption: String,
        onOptionSelected: (String) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }

        Column {
            Text(text = label)
            Box {
                OutlinedButton(onClick = { expanded = true }) {
                    Text(selectedOption)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                onOptionSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun DateSelector(
        label: String,
        selectedDate: LocalDate,
        onDateSelected: (LocalDate) -> Unit
    ) {



    }
}