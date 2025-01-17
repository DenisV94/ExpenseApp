package org.denis.expenseapp.presentation.ui.addScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.add_screen_amount
import expenseapp.composeapp.generated.resources.add_screen_amount_hint
import expenseapp.composeapp.generated.resources.add_screen_category
import expenseapp.composeapp.generated.resources.add_screen_date
import expenseapp.composeapp.generated.resources.add_screen_description
import expenseapp.composeapp.generated.resources.add_screen_description_hint
import expenseapp.composeapp.generated.resources.add_screen_error_empty_amount
import expenseapp.composeapp.generated.resources.add_screen_save_button
import expenseapp.composeapp.generated.resources.add_screen_title
import expenseapp.composeapp.generated.resources.button_confirmation_basic
import org.denis.expenseapp.presentation.common.BodyTextLarge
import org.denis.expenseapp.presentation.common.BodyTextMedium
import org.denis.expenseapp.presentation.common.ButtonPrimary
import org.denis.expenseapp.presentation.common.CurrencyInputField
import org.denis.expenseapp.presentation.common.LoadingState
import org.denis.expenseapp.presentation.common.MainBodyStyle
import org.denis.expenseapp.presentation.common.TextEditField
import org.denis.expenseapp.presentation.common.VisibleDatePicker
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiAction
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiState
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModel
import org.denis.expenseapp.presentation.model.addScreen.CategoryUiModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

class AddScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<AddExpenseViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        // Collect UI state
        val uiState = viewModel.uiState.collectAsState().value

        Scaffold(
            topBar = {
                BackButtonTopBar(
                    title = stringResource(Res.string.add_screen_title),
                    onBackButtonPressed = { navigator.pop() }
                )
            },
            content = { paddingValues ->
                AddScreenBody(
                    uiState = uiState,
                    onAction = viewModel::onSaveIncomePressed,
                    paddingValues = paddingValues,
                    navigator = navigator
                )
            }
        )
    }

    @Composable
    private fun AddScreenBody(
        uiState: AddExpenseUiState,
        onAction: (AddExpenseUiAction) -> Unit,
        paddingValues: PaddingValues,
        navigator: Navigator
    ) {
        MainBodyStyle(
            paddingValues = paddingValues
        ) {
            when (uiState) {
                is AddExpenseUiState.Loading -> LoadingState()
                is AddExpenseUiState.ExpenseUiState -> ExpenseForm(
                    uiState = uiState,
                    onAction = onAction
                )

                is AddExpenseUiState.RegisterCompleted -> {
                    navigator.pop()
                }

                is AddExpenseUiState.Error -> ErrorState(
                    onRetry = { onAction(AddExpenseUiAction.Retry) }
                )
            }
        }
    }

    @Composable
    private fun ErrorState(onRetry: () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                BodyTextLarge(
                    text = stringResource(Res.string.add_screen_error_empty_amount)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ButtonPrimary(
                    text = stringResource(Res.string.button_confirmation_basic),
                    onClick = onRetry,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    private fun ExpenseForm(
        uiState: AddExpenseUiState.ExpenseUiState,
        onAction: (AddExpenseUiAction) -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        // Date Field
                        BodyTextMedium(text = stringResource(Res.string.add_screen_date))
                        Spacer(modifier = Modifier.height(8.dp))
                        VisibleDatePicker(
                            onDateSelected = { onAction(AddExpenseUiAction.SelectDate(it)) }
                        )
                    }

                    item {
                        // Amount Field
                        BodyTextMedium(text = stringResource(Res.string.add_screen_amount))
                        Spacer(modifier = Modifier.height(8.dp))
                        CurrencyInputField(
                            value = uiState.amount,
                            onValueChange = { onAction(AddExpenseUiAction.UpdateAmount(it)) },
                            label = stringResource(Res.string.add_screen_amount_hint),
                            modifier = Modifier.fillMaxWidth(),
                            onImeAction = {}
                        )
                    }

                    item {
                        // Description Field
                        BodyTextMedium(text = stringResource(Res.string.add_screen_description))
                        Spacer(modifier = Modifier.height(8.dp))
                        TextEditField(
                            value = uiState.description,
                            onValueChange = { onAction(AddExpenseUiAction.UpdateDescription(it)) },
                            label = stringResource(Res.string.add_screen_description_hint),
                            modifier = Modifier.fillMaxWidth(),
                            onImeAction = {}
                        )
                    }

                    item {
                        BodyTextMedium(text = stringResource(Res.string.add_screen_category))
                        Spacer(modifier = Modifier.height(8.dp))
                        CategoriesList(
                            categories = uiState.categories,
                            selectedCategory = uiState.selectedCategory,
                            onCategorySelected = { category ->
                                onAction(AddExpenseUiAction.SelectCategory(category)) // Pass the CategoryUiModel
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Save Button
                ButtonPrimary(
                    text = stringResource(Res.string.add_screen_save_button),
                    onClick = { onAction(AddExpenseUiAction.SaveExpense) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun CategoriesList(
        categories: List<CategoryUiModel>,
        selectedCategory: CategoryUiModel,
        onCategorySelected: (CategoryUiModel) -> Unit
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = category.id == selectedCategory.id

                androidx.compose.material3.OutlinedButton(
                    onClick = { onCategorySelected(category) },
                    modifier = Modifier.padding(horizontal = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        contentColor = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = stringResource(category.title),
                        color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }


}