package org.denis.expenseapp.domain.models

import kotlinx.datetime.LocalDate

data class Expense(
    val id: Int? = null,
    val description: String,
    val amount: Double,
    val date: LocalDate,
    val category: ExpenseCategory
)