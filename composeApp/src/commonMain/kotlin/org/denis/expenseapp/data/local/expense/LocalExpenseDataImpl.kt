package org.denis.expenseapp.data.local.expense

import app.cash.sqldelight.db.SqlDriver
import arrow.core.Either
import com.denis.expenseapp.Database
import org.denis.expenseapp.domain.EitherResult
import org.denis.expenseapp.domain.models.Expense

class LocalExpenseDataImpl(driver: SqlDriver): LocalExpenseData {
    private val db by lazy { Database(driver) }

    private suspend fun <T> executeQuery(query: suspend () -> T): Either<EitherResult.LocalDatabaseError, T> {
        return try {
            Either.Right(query())
        } catch (e: Exception) {
            Either.Left(EitherResult.LocalDatabaseError.ConnectionError)
        }
    }

    override suspend fun createExpense(expense: Expense): Either<EitherResult.LocalDatabaseError, EitherResult.Success> {
        return executeQuery {
           /* db.expenseQueries.insertExpense(
                amount = expense.amount,
                description = expense.description,
                date = expense.date
            )
            EitherResult.Success*/
            TODO()
        }
    }

    override suspend fun getExpenses(): Either<EitherResult.LocalDatabaseError, List<Expense>> {
        return TODO()
    }

    override suspend fun deleteExpense(id: Int): Either<EitherResult.LocalDatabaseError, Boolean> {
        return TODO()
    }
}