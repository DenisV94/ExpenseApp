package org.denis.expenseapp.presentation.model.homeScreen

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(
        val userExpenses: List<ExpenseUiModel>
    ) : HomeUiState()
    data object Error : HomeUiState()
}

sealed class HomeUiAction {
    data class UpdateDescription(val description: String) : HomeUiAction()
    data class UpdateAmount(val amount: String) : HomeUiAction()
    data class SelectCategory(val category: HomeUiState) : HomeUiAction()
    data class ExpenseDetailsPressed(val expense: ExpenseUiModel) : HomeUiAction()
}
