package org.denis.expenseapp.presentation.model.detailsScreen

sealed class DetailsUiState {
    data object Loading : DetailsUiState()
    data class Success(
        val detailsExpense: DetailsUiModel
    ) : DetailsUiState()

    data object Error : DetailsUiState()
}

