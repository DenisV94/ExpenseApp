package org.denis.expenseapp.data.local.expense

import arrow.core.Either
import org.denis.expenseapp.domain.EitherResult
import org.denis.expenseapp.domain.models.Expense

interface LocalExpenseData {
    suspend fun createExpense(expense: Expense): Either<EitherResult.LocalDatabaseError, EitherResult.Success>
    suspend fun getExpenses(): Either<EitherResult.LocalDatabaseError, List<Expense>>
    suspend fun deleteExpense(id: Int): Either<EitherResult.LocalDatabaseError, Boolean>
}