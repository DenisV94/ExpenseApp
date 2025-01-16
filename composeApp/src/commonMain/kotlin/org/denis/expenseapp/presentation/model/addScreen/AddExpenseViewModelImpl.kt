package org.denis.expenseapp.presentation.model.addScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.utils.now
import org.denis.expenseapp.data.repository.expenses.ExpenseRepository
import org.denis.expenseapp.domain.models.Expense

class AddExpenseViewModelImpl(
    private val repository: ExpenseRepository
) : AddExpenseViewModel() {

    private val _uiState = MutableStateFlow<AddExpenseUiState>(AddExpenseUiState.Loading)
    override val uiState: StateFlow<AddExpenseUiState> = _uiState

    init {
        viewModelScope.launch {
            val categories = repository.getCategoryList().map { it.toCategoryUiModel() }
            _uiState.value = AddExpenseUiState.ExpenseUiState(
                description = "",
                amount = "",
                selectedCategory = categories.first(),
                date = LocalDate.now(),
                categories = categories
            )
        }
    }

    override fun onSaveIncomePressed(action: AddExpenseUiAction) {
        when (action) {
            is AddExpenseUiAction.UpdateDescription -> updateDescription(action.description)
            is AddExpenseUiAction.UpdateAmount -> updateAmount(action.amount)
            is AddExpenseUiAction.SelectCategory -> selectCategory(action.category)
            is AddExpenseUiAction.SelectDate -> selectDate(action.date)
            is AddExpenseUiAction.SaveExpense -> saveExpense()
        }
    }

    private fun updateDescription(description: String) {
        _uiState.update { currentState ->
            if (currentState is AddExpenseUiState.ExpenseUiState) {
                currentState.copy(description = description)
            } else currentState
        }
    }

    private fun updateAmount(amount: String) {
        _uiState.update { currentState ->
            if (currentState is AddExpenseUiState.ExpenseUiState) {
                currentState.copy(amount = amount)
            } else currentState
        }
    }

    private fun selectCategory(category: CategoryUiModel) {
        _uiState.update { currentState ->
            if (currentState is AddExpenseUiState.ExpenseUiState) {
                currentState.copy(selectedCategory = category)
            } else currentState
        }
    }

    private fun selectDate(date: LocalDate) {
        _uiState.update { currentState ->
            if (currentState is AddExpenseUiState.ExpenseUiState) {
                currentState.copy(date = date)
            } else currentState
        }
    }

    private fun saveExpense() {
        val currentState = _uiState.value

        if (currentState is AddExpenseUiState.ExpenseUiState) {
            if (currentState.description.isBlank() || currentState.amount.isBlank()) {
                return
            }

            val expense = Expense(
                id = 0, // Assuming 0 for new expense, replace with actual logic
                description = currentState.description,
                amount = currentState.amount.toLong(),
                category = currentState.selectedCategory.id,
                date = currentState.date
            )
            viewModelScope.launch {
                val result = repository.addExpense(expense)
                if (result.isRight()) {

                } else {
                    TODO()
                }
            }
        }
    }
}