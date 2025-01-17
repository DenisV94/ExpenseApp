package org.denis.expenseapp.presentation.model.detailsScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.denis.expenseapp.data.repository.expenses.ExpenseRepository
import org.denis.expenseapp.domain.models.Expense
import org.denis.expenseapp.domain.models.ExpenseCategory

class DetailsViewModelImpl(
    private val repository: ExpenseRepository
) : DetailsViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    override val uiState: StateFlow<DetailsUiState> = _uiState

    override fun selectedExpense(expenseId: Long) {
        viewModelScope.launch {
            val categoryList = repository.getCategoryList()
            repository.getAllExpenses().fold(
                { _uiState.value = DetailsUiState.Error },
                { expenses ->
                    val expense = expenses.find { it.id == expenseId }
                    if (expense != null) {
                        val expenseUiModel = composeDetailsUiModel(expense, categoryList)
                        _uiState.value = DetailsUiState.Success(expenseUiModel)
                    } else {
                        _uiState.value = DetailsUiState.Error
                    }
                }
            )
        }
    }

    private fun composeDetailsUiModel(
        expense: Expense,
        categoryList: List<ExpenseCategory>
    ): DetailsUiModel {
        // Create the element for the Ui
        // Find the category based on the ID
        val category = categoryList.find { it.id.toLong() == expense.id } ?: ExpenseCategory.OTHER

        return DetailsUiModel(
            amount = expense.amount.toString(),
            description = expense.description,
            date = expense.date.toString(),
            iconResId = category.icon,
            categoryName = category.descriptionResId
        )
    }

}