package org.denis.expenseapp.presentation.model.detailsScreen

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class DetailsUiModel(
    val amount: String,
    val description: String,
    val date: String,
    val iconResId: DrawableResource,
    val categoryName: StringResource
)
