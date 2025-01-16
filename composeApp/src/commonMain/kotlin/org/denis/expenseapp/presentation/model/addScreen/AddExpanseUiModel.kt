package org.denis.expenseapp.presentation.model.addScreen

import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.ic_arrow_back
import org.denis.expenseapp.domain.models.ExpenseCategory
import org.denis.expenseapp.domain.models.ExpenseCategoryIcon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class CategoryUiModel(
    val id: String,
    val icon: DrawableResource,
    val title: StringResource
)

fun ExpenseCategory.toCategoryUiModel(): CategoryUiModel {
    val iconResId = when (this.icon) {
        ExpenseCategoryIcon.FOOD -> Res.drawable.ic_arrow_back
        ExpenseCategoryIcon.TRANSPORT -> Res.drawable.ic_arrow_back
        ExpenseCategoryIcon.ENTERTAINMENT ->Res.drawable.ic_arrow_back
        ExpenseCategoryIcon.HEALTH -> Res.drawable.ic_arrow_back
        ExpenseCategoryIcon.UTILITIES -> Res.drawable.ic_arrow_back
        ExpenseCategoryIcon.OTHER -> Res.drawable.ic_arrow_back
    }
    return CategoryUiModel(
        id = this.id.toString(),
        icon = iconResId,
        title = this.descriptionResId
    )
}