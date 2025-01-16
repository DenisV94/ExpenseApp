package org.denis.expenseapp.presentation.ui.addScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.add_screen_amount
import expenseapp.composeapp.generated.resources.add_screen_amount_hint
import expenseapp.composeapp.generated.resources.add_screen_category
import expenseapp.composeapp.generated.resources.add_screen_date
import expenseapp.composeapp.generated.resources.add_screen_description
import expenseapp.composeapp.generated.resources.add_screen_description_hint
import expenseapp.composeapp.generated.resources.add_screen_save_button
import expenseapp.composeapp.generated.resources.add_screen_title
import org.denis.expenseapp.presentation.common.VisibleDatePicker
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiAction
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiState
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModel
import org.denis.expenseapp.presentation.model.addScreen.CategoryUiModel
import org.denis.expenseapp.presentation.theme.ButtonPrimary
import org.denis.expenseapp.presentation.theme.CurrencyInputField
import org.denis.expenseapp.presentation.theme.MainBodyStyle
import org.denis.expenseapp.presentation.theme.TextEditField
import org.denis.expenseapp.presentation.theme.TextEditTitle
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
                    paddingValues = paddingValues
                )
            }
        )
    }

    @Composable
    private fun AddScreenBody(
        uiState: AddExpenseUiState,
        onAction: (AddExpenseUiAction) -> Unit,
        paddingValues: PaddingValues
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
                is AddExpenseUiState.Error -> ErrorState()
            }
        }
    }

    @Composable
    private fun LoadingState() {
        Box(modifier = Modifier.fillMaxWidth()) {

        }
    }

    @Composable
    private fun ErrorState() {
        Box(modifier = Modifier.fillMaxWidth()) {

        }
    }

    @Composable
    private fun ExpenseForm(
        uiState: AddExpenseUiState.ExpenseUiState,
        onAction: (AddExpenseUiAction) -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Date Field
            TextEditTitle(text = stringResource(Res.string.add_screen_date))
            VisibleDatePicker(
                onDateSelected = { onAction(AddExpenseUiAction.SelectDate(it)) }
            )

            // Description Field
            TextEditTitle(text = stringResource(Res.string.add_screen_description))
            TextEditField(
                value = uiState.description,
                onValueChange = { onAction(AddExpenseUiAction.UpdateDescription(it)) },
                label = stringResource(Res.string.add_screen_description_hint),
                modifier = Modifier.fillMaxWidth(),
                onImeAction = {}
            )

            // Amount Field
            TextEditTitle(text = stringResource(Res.string.add_screen_amount))
            CurrencyInputField(
                value = uiState.amount,
                onValueChange = { onAction(AddExpenseUiAction.UpdateAmount(it)) },
                label = stringResource(Res.string.add_screen_amount_hint),
                modifier = Modifier.fillMaxWidth(),
                onImeAction = {}
            )

            TextEditTitle(text = stringResource(Res.string.add_screen_category))
            CategoriesList(
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = { category ->
                    onAction(AddExpenseUiAction.SelectCategory(category)) // Pass the CategoryUiModel
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Save Button
            ButtonPrimary(
                text = stringResource(Res.string.add_screen_save_button),
                onClick = { onAction(AddExpenseUiAction.SaveExpense) },
                modifier = Modifier.fillMaxWidth()
            )
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