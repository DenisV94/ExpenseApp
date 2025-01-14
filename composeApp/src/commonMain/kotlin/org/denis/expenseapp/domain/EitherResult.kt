package org.denis.expenseapp.domain

sealed class EitherResult<out T> {
    data object Success : EitherResult<Nothing>()

    // Errors
    sealed class RepositoryError : EitherResult<Nothing>() {
        data object NotFound : RepositoryError()
        data class UnknownError(val message: String) : RepositoryError()
    }

    sealed class LocalDatabaseError : RepositoryError() {
        data object ConnectionError : LocalDatabaseError()
        data object NotFound : LocalDatabaseError()
    }
}