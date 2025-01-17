package org.denis.expenseapp.domain

sealed class EitherResult<out T> {
    data object Success : EitherResult<Nothing>()

    // General Repository Errors
    sealed class RepositoryError : EitherResult<Nothing>() {
        data object NotFound : RepositoryError()
        data object InternalError : RepositoryError()
        data class ValidationError(val message: String) : RepositoryError()
    }

    // Local database-specific errors
    sealed class LocalDatabaseError : RepositoryError() {
        data object ConnectionError : LocalDatabaseError()
        data object ConstraintError : LocalDatabaseError()
    }
}