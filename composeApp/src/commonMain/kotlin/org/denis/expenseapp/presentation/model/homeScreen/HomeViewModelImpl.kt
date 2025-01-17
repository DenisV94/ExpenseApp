package org.denis.expenseapp.presentation.model.homeScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.denis.expenseapp.data.repository.expenses.ExpenseRepository
import org.denis.expenseapp.domain.models.Expense
import org.denis.expenseapp.domain.models.ExpenseCategory

class HomeViewModelImpl(
    private val repository: ExpenseRepository
) : HomeViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    override val uiState: StateFlow<HomeUiState> = _uiState

    override fun reloadView() {
        viewModelScope.launch {
            val categoryList = repository.getCategoryList()
            repository.getAllExpenses().fold(
                { _uiState.value = HomeUiState.Error },
                { expenses ->
                    val expenseUiModels = composeExpenseUiModelList(expenses, categoryList)
                    _uiState.value = HomeUiState.Success(expenseUiModels)
                }
            )
        }
    }

    private fun composeExpenseUiModelList(
        expenses: List<Expense>,
        categoryList: List<ExpenseCategory>
    ): List<ExpenseUiModel> {
        return expenses.map { expense ->
            // Find the category based on the ID
            val category =
                categoryList.find { it.id.toLong() == expense.id } ?: ExpenseCategory.OTHER

            ExpenseUiModel(
                id = expense.id!!,
                amount = expense.amount.toString(),
                date = expense.date.toString(),
                iconResId = category.icon,
                categoryName = category.descriptionResId
            )
        }
    }

}