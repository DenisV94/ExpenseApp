package org.denis.expenseapp

import android.app.Application

open class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppContext.setUp(this)

    }

}