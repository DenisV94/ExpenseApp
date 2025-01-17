package org.denis.expenseapp.data.repository.expenses

import arrow.core.Either
import kotlinx.datetime.LocalDate
import org.denis.expenseapp.data.local.expense.LocalExpenseData
import org.denis.expenseapp.domain.EitherResult
import org.denis.expenseapp.domain.models.Expense
import org.denis.expenseapp.domain.models.ExpenseCategory

class ExpenseRepositoryImpl(
    private val database: LocalExpenseData
) : ExpenseRepository {
    override suspend fun getAllExpenses(): Either<EitherResult.RepositoryError, List<Expense>> {
        return try {
            when (val result = database.getExpenses()) {
                is Either.Right -> {
                    Either.Right(result.value)
                }

                is Either.Left -> Either.Left(EitherResult.RepositoryError.InternalError)
            }
        } catch (e: Exception) {
            Either.Left(EitherResult.RepositoryError.InternalError)
        }
    }

    override suspend fun addExpense(expense: Expense): Either<EitherResult.RepositoryError, EitherResult.Success> {
        return try {
            database.createExpense(expense)
            Either.Right(EitherResult.Success)
        } catch (e: Exception) {
            Either.Left(EitherResult.RepositoryError.InternalError)
        }
    }

    override suspend fun getCategoryList(): List<ExpenseCategory> {
        return ExpenseCategory.entries
    }
}