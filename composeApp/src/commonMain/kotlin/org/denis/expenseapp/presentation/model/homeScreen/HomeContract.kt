package org.denis.expenseapp.presentation.model.homeScreen

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(
        val userExpenses: List<HomeUiModel>
    ) : HomeUiState()

    data object Error : HomeUiState()
}