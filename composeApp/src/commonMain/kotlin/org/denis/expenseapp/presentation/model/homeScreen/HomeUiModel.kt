package org.denis.expenseapp.presentation.model.homeScreen

import org.denis.expenseapp.domain.models.Expense
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class ExpenseUiModel(
    val id:Long,
    val amount: String,
    val date: String,
    val iconResId: DrawableResource,
    val categoryName: StringResource
)
