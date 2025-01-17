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
        loadInitialState()
    }

    override fun onSaveIncomePressed(action: AddExpenseUiAction) {
        // Ui Actions
        when (action) {
            is AddExpenseUiAction.UpdateDescription -> updateUiState { it.copy(description = action.description) }
            is AddExpenseUiAction.UpdateAmount -> updateUiState { it.copy(amount = action.amount) }
            is AddExpenseUiAction.SelectCategory -> updateUiState { it.copy(selectedCategory = action.category) }
            is AddExpenseUiAction.SelectDate -> updateUiState { it.copy(date = action.date) }
            is AddExpenseUiAction.SaveExpense -> saveExpense()
            is AddExpenseUiAction.Retry -> loadInitialState()
            is AddExpenseUiAction.SuccessConfirmation -> loadInitialState()
        }
    }

    private fun loadInitialState() {
        viewModelScope.launch {
            val categories = repository.getCategoryList().map { it.toCategoryUiModel() }
            if (categories.isNotEmpty()) {
                _uiState.value = AddExpenseUiState.ExpenseUiState(
                    description = "",
                    amount = "",
                    selectedCategory = categories.first(),
                    date = LocalDate.now(),
                    categories = categories
                )
            } else {
                _uiState.value = AddExpenseUiState.Error
            }
        }
    }

    private fun saveExpense() {
        val currentState = (_uiState.value as? AddExpenseUiState.ExpenseUiState) ?: return

        if (!isInputValid(currentState)) {
            _uiState.update { AddExpenseUiState.Error }
            return
        }

        val expense = Expense(
            id = 0, // id is not used in this case
            description = currentState.description,
            amount = currentState.amount.toLong(),
            category = currentState.selectedCategory.id,
            date = currentState.date
        )

        viewModelScope.launch {
            _uiState.update { AddExpenseUiState.Loading }
            val result = repository.addExpense(expense)
            _uiState.update {
                if (result.isRight()) {
                    AddExpenseUiState.RegisterCompleted
                } else {
                    AddExpenseUiState.Error
                }
            }
        }
    }

    private fun isInputValid(state: AddExpenseUiState.ExpenseUiState): Boolean {
        return state.description.isNotBlank() && state.amount.isNotBlank() && state.amount.toLongOrNull() != null
    }

    private fun updateUiState(transform: (AddExpenseUiState.ExpenseUiState) -> AddExpenseUiState.ExpenseUiState) {
        _uiState.update { currentState ->
            if (currentState is AddExpenseUiState.ExpenseUiState) {
                transform(currentState)
            } else {
                currentState
            }
        }
    }
}