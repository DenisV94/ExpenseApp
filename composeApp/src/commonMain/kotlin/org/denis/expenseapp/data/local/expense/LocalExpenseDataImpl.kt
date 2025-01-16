package org.denis.expenseapp.data.local.expense

import app.cash.sqldelight.db.SqlDriver
import arrow.core.Either
import com.denis.expenseapp.Database
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.denis.expenseapp.domain.EitherResult
import org.denis.expenseapp.domain.models.Expense

class LocalExpenseDataImpl(driver: SqlDriver) : LocalExpenseData {

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
            db.expenseQueries.insertExpense(
                description = expense.description,
                amount = expense.amount,
                date = expense.date.toString(),
                category = expense.category.toLong()
            )
            EitherResult.Success
        }
    }

    override suspend fun getExpenses(): Either<EitherResult.LocalDatabaseError, List<Expense>> {
        return executeQuery {
            db.expenseQueries.selectAllExpenses()
                .executeAsList()
                .map { it.toDomain() }
        }
    }

    override suspend fun deleteExpense(id: Long): Either<EitherResult.LocalDatabaseError, EitherResult.Success> {
        return executeQuery {
            db.expenseQueries.deleteExpenseById(id)
            EitherResult.Success
        }
    }

    override suspend fun updateExpense(expense: Expense): Either<EitherResult.LocalDatabaseError, EitherResult.Success> {
        return executeQuery {
            val expenseId = expense.id ?: throw IllegalArgumentException("Expense ID must not be null for updates.")
            db.expenseQueries.updateExpense(
                description = expense.description,
                amount = expense.amount,
                date = expense.date.toString(),
                category = expense.category.toLong(),
                id = expenseId
            )
            EitherResult.Success
        }
    }

    // Extension function to map SQLDelight-generated model to domain model
    private fun com.denis.expenseapp.Expense.toDomain(): Expense {
        return Expense(
            id = this.id,
            description = this.description,
            amount = this.amount,
            date = LocalDate.parse(this.date),
            category = this.category.toInt()
        )
    }
}