package org.denis.expenseapp.data.local.sqlDriver

import app.cash.sqldelight.db.SqlDriver
import com.denis.expenseapp.Database


expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driver: SqlDriver): Database {
    return Database(
        driver = driver
    )
}