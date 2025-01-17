package org.denis.expenseapp.presentation.model.addScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class AddExpenseViewModel : ViewModel() {
    abstract val uiState: StateFlow<AddExpenseUiState>
    abstract fun onSaveIncomePressed(action: AddExpenseUiAction)
}