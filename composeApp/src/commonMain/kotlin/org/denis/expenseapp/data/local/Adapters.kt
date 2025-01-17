package org.denis.expenseapp.data.local

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDate

val LocalDateAdapter = object : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate = LocalDate.parse(databaseValue)
    override fun encode(value: LocalDate): String = value.toString()
}