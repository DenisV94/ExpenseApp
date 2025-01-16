package org.denis.expenseapp.presentation.model.addScreen

import kotlinx.datetime.LocalDate

sealed class AddExpenseUiState {
    object Loading : AddExpenseUiState()
    data class Success(
        val description: String,
        val amount: String,
        val category: String,
        val date: LocalDate
    ) : AddExpenseUiState()
    object Error : AddExpenseUiState()
}

// UiAction: Represents actions/events triggered by the user
sealed class AddExpenseUiAction {
    data class UpdateDescription(val description: String) : AddExpenseUiAction()
    data class UpdateAmount(val amount: String) : AddExpenseUiAction()
    data class SelectCategory(val category: String) : AddExpenseUiAction()
    data class SelectDate(val date: LocalDate) : AddExpenseUiAction()
    object SaveExpense : AddExpenseUiAction()
}