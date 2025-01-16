package org.denis.expenseapp.domain.models

import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.category_entertainment
import expenseapp.composeapp.generated.resources.category_food
import expenseapp.composeapp.generated.resources.category_health
import expenseapp.composeapp.generated.resources.category_other
import expenseapp.composeapp.generated.resources.category_transport
import expenseapp.composeapp.generated.resources.category_utilities
import expenseapp.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource



enum class ExpenseCategory(
    val id: Int,
    val descriptionResId: StringResource,
    val icon: DrawableResource
) {
    FOOD(id=1, descriptionResId=Res.string.category_food, icon= Res.drawable.ic_arrow_back),
    TRANSPORT(2, Res.string.category_transport, Res.drawable.ic_arrow_back),
    ENTERTAINMENT(3, Res.string.category_entertainment, Res.drawable.ic_arrow_back),
    HEALTH(4, Res.string.category_health, Res.drawable.ic_arrow_back),
    UTILITIES(5, Res.string.category_utilities, Res.drawable.ic_arrow_back),
    OTHER(6, Res.string.category_other, Res.drawable.ic_arrow_back);
}