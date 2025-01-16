package org.denis.expenseapp.domain.models

import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.category_entertainment
import expenseapp.composeapp.generated.resources.category_food
import expenseapp.composeapp.generated.resources.category_health
import expenseapp.composeapp.generated.resources.category_other
import expenseapp.composeapp.generated.resources.category_transport
import expenseapp.composeapp.generated.resources.category_utilities
import org.jetbrains.compose.resources.StringResource


enum class ExpenseCategoryIcon {
    FOOD,
    TRANSPORT,
    ENTERTAINMENT,
    HEALTH,
    UTILITIES,
    OTHER;
    val iconName: String = name.lowercase()
}

enum class ExpenseCategory(
    val id: Int,
    val descriptionResId: StringResource,
    val icon: ExpenseCategoryIcon
) {
    FOOD(1, Res.string.category_food, ExpenseCategoryIcon.FOOD),
    TRANSPORT(2, Res.string.category_transport, ExpenseCategoryIcon.TRANSPORT),
    ENTERTAINMENT(3, Res.string.category_entertainment, ExpenseCategoryIcon.ENTERTAINMENT),
    HEALTH(4, Res.string.category_health, ExpenseCategoryIcon.HEALTH),
    UTILITIES(5, Res.string.category_utilities, ExpenseCategoryIcon.UTILITIES),
    OTHER(6, Res.string.category_other, ExpenseCategoryIcon.OTHER);
}