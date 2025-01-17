package org.denis.expenseapp.presentation.model.detailsScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class DetailsViewModel : ViewModel() {
    abstract val uiState: StateFlow<DetailsUiState>
    abstract fun selectedExpense(expenseId: Long)
}