package org.denis.expenseapp.domain.models

import kotlinx.datetime.LocalDate

data class Expense(
    val id: Long? = null,
    val description: String,
    val amount: Long,
    val date: LocalDate,
    val category: Int
)