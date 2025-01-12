package org.denis.expenseapp.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        //modules(appModule())
    }
}

fun appModule() = listOf(TODO())