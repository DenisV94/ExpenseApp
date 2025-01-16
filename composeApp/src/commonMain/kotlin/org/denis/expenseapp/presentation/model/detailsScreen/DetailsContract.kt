package org.denis.expenseapp.presentation.model.detailsScreen

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.denis.expenseapp.domain.models.Expense

sealed class DetailsUiState {
    data object Loading : DetailsUiState()
    data class Success(
        val detailsExpense: DetailsUiModel
    ) : DetailsUiState()
    data object Error : DetailsUiState()
}

sealed class HomeUiAction {
    data class UpdateDescription(val description: String) : HomeUiAction()
    data class UpdateAmount(val amount: String) : HomeUiAction()
    data class SelectCategory(val category: DetailsUiModel) : HomeUiAction()
    data class ExpenseDetailsPressed(val expense: DetailsUiModel) : HomeUiAction()
}
