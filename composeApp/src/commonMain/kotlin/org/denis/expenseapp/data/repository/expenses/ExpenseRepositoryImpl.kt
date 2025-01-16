package org.denis.expenseapp.data.repository.expenses

import arrow.core.Either
import com.denis.expenseapp.Database
import kotlinx.datetime.LocalDate
import org.denis.expenseapp.domain.EitherResult
import org.denis.expenseapp.domain.models.Expense
import org.denis.expenseapp.domain.models.ExpenseCategory

class ExpenseRepositoryImpl(
    private val database: Database
) : ExpenseRepository {
    override suspend fun getAllExpenses(): Either<EitherResult.RepositoryError, List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getExpensesByDateRange(
        startDate: LocalDate,
        endDate: LocalDate
    ): Either<EitherResult.RepositoryError, List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getExpenseById(id: String): Either<EitherResult.RepositoryError, Expense> {
        TODO("Not yet implemented")
    }

    override suspend fun addExpense(expense: Expense): Either<EitherResult.RepositoryError, EitherResult.Success> {
        TODO("Not yet implemented")
    }

    override suspend fun updateExpense(expense: Expense): Either<EitherResult.RepositoryError, EitherResult.Success> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteExpense(id: String): Either<EitherResult.RepositoryError, EitherResult.Success> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryList(): List<ExpenseCategory> {
        return ExpenseCategory.values().toList()
    }

}