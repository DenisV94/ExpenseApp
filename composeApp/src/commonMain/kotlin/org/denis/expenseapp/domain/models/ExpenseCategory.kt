package org.denis.expenseapp.domain.models

import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.category_entertainment
import expenseapp.composeapp.generated.resources.category_food
import expenseapp.composeapp.generated.resources.category_health
import expenseapp.composeapp.generated.resources.category_other
import expenseapp.composeapp.generated.resources.category_transport
import expenseapp.composeapp.generated.resources.ic_entretainment
import expenseapp.composeapp.generated.resources.ic_food
import expenseapp.composeapp.generated.resources.ic_health
import expenseapp.composeapp.generated.resources.ic_other
import expenseapp.composeapp.generated.resources.ic_transport
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


enum class ExpenseCategory(
    val id: Int,
    val descriptionResId: StringResource,
    val icon: DrawableResource
) {
    FOOD(id = 1, descriptionResId = Res.string.category_food, icon = Res.drawable.ic_food),
    TRANSPORT(
        id = 2,
        descriptionResId = Res.string.category_transport,
        icon = Res.drawable.ic_transport
    ),
    ENTERTAINMENT(
        id = 3,
        descriptionResId = Res.string.category_entertainment,
        icon = Res.drawable.ic_entretainment
    ),
    HEALTH(id = 4, descriptionResId = Res.string.category_health, icon = Res.drawable.ic_health),
    OTHER(id = 5, descriptionResId = Res.string.category_other, icon = Res.drawable.ic_other);
}