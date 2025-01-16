package org.denis.expenseapp.di


import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModel
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModelImpl
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

fun appModule() = listOf(
    addExpenseModule
)

val addExpenseModule = module {
    // Bind AddExpenseViewModel interface to its implementation
    viewModel<AddExpenseViewModel> { AddExpenseViewModelImpl() }
}
