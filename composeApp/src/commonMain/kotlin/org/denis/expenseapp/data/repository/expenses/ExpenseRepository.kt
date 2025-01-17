package org.denis.expenseapp.data.repository.expenses

import arrow.core.Either
import kotlinx.datetime.LocalDate
import org.denis.expenseapp.domain.EitherResult
import org.denis.expenseapp.domain.models.Expense
import org.denis.expenseapp.domain.models.ExpenseCategory

interface ExpenseRepository {

    suspend fun getAllExpenses(): Either<EitherResult.RepositoryError, List<Expense>>

    suspend fun addExpense(expense: Expense): Either<EitherResult.RepositoryError, EitherResult.Success>

    suspend fun getCategoryList(): List<ExpenseCategory>

}