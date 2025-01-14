package org.denis.expenseapp.domain.models

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
    val description: String,
    val icon: ExpenseCategoryIcon
) {
    FOOD("Food and Dining", ExpenseCategoryIcon.FOOD),
    TRANSPORT("Transportation", ExpenseCategoryIcon.TRANSPORT),
    ENTERTAINMENT("Entertainment", ExpenseCategoryIcon.ENTERTAINMENT),
    HEALTH("Health and Fitness", ExpenseCategoryIcon.HEALTH),
    UTILITIES("Utilities and Bills", ExpenseCategoryIcon.UTILITIES),
    OTHER("Other Expenses", ExpenseCategoryIcon.OTHER);
}