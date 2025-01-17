package org.denis.expenseapp.presentation.model.homeScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class HomeViewModel : ViewModel() {
    abstract val uiState: StateFlow<HomeUiState>
    abstract fun reloadView()
}