package org.denis.expenseapp.presentation.model.homeScreen

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class HomeUiModel(
    val id: Long,
    val amount: String,
    val date: String,
    val iconResId: DrawableResource,
    val categoryName: StringResource
)
