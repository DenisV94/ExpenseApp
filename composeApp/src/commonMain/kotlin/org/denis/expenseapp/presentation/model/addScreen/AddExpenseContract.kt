package org.denis.expenseapp.presentation.model.addScreen

import kotlinx.datetime.LocalDate

sealed class AddExpenseUiState {
    data object Loading : AddExpenseUiState()
    data class ExpenseUiState(
        val description: String,
        val amount: String,
        val selectedCategory: CategoryUiModel,
        val date: LocalDate,
        val categories: List<CategoryUiModel>
    ) : AddExpenseUiState()

    data object Error : AddExpenseUiState()
    data object RegisterCompleted : AddExpenseUiState()
}

// UiAction: Represents actions/events triggered by the user
sealed class AddExpenseUiAction {
    data class UpdateDescription(val description: String) : AddExpenseUiAction()
    data class UpdateAmount(val amount: String) : AddExpenseUiAction()
    data class SelectCategory(val category: CategoryUiModel) : AddExpenseUiAction()
    data class SelectDate(val date: LocalDate) : AddExpenseUiAction()
    data object SaveExpense : AddExpenseUiAction()
    data object Retry: AddExpenseUiAction()
    data object SuccessConfirmation: AddExpenseUiAction()
}