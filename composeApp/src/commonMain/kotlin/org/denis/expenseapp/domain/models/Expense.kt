package org.denis.expenseapp.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class Expense(
    val id: Long? = null,
    val description: String,
    val amount: Double,
    val date: LocalDateTime,
    val category: ExpenseCategory
)