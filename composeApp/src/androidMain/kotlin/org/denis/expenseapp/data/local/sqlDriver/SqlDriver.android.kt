package org.denis.expenseapp.data.local.sqlDriver

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.denis.expenseapp.Database
import org.denis.expenseapp.AppContext

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = Database.Schema,
            context = AppContext.get(),
            name = "expenseApp.db"
        )
    }
}