package org.denis.expenseapp.presentation.model.addScreen

import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.ic_arrow_back
import org.denis.expenseapp.domain.models.ExpenseCategory
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class CategoryUiModel(
    val id: Int,
    val icon: DrawableResource,
    val title: StringResource
)

fun ExpenseCategory.toCategoryUiModel(): CategoryUiModel {
    return CategoryUiModel(
        id = this.id,
        icon = this.icon,
        title = this.descriptionResId
    )
}