package org.denis.expenseapp.di

import org.denis.expenseapp.data.local.expense.LocalExpenseData
import org.denis.expenseapp.data.local.expense.LocalExpenseDataImpl
import org.denis.expenseapp.data.local.sqlDriver.DriverFactory
import org.denis.expenseapp.data.local.sqlDriver.createDatabase
import org.denis.expenseapp.data.repository.expenses.ExpenseRepository
import org.denis.expenseapp.data.repository.expenses.ExpenseRepositoryImpl
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModel
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModelImpl
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsViewModel
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsViewModelImpl
import org.denis.expenseapp.presentation.model.homeScreen.HomeViewModel
import org.denis.expenseapp.presentation.model.homeScreen.HomeViewModelImpl
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

fun appModule() = listOf(
    databaseModule,
    homeScreenModule,
    addExpenseScreenModule,
    detailsScreenModule
)

val homeScreenModule = module {
    viewModel<HomeViewModel> { HomeViewModelImpl(repository = get()) }
}

val addExpenseScreenModule = module {
    viewModel<AddExpenseViewModel> { AddExpenseViewModelImpl(repository = get()) }
}

val detailsScreenModule = module {
    viewModel<DetailsViewModel> { DetailsViewModelImpl(repository = get()) }
}

val databaseModule = module {
    // Provide the SQLDelight driver
    single { DriverFactory().createDriver() }

    single {
        createDatabase(driver = get())
    }

    single<LocalExpenseData> {
        LocalExpenseDataImpl(driver = get())
    }

    single<ExpenseRepository> {
        ExpenseRepositoryImpl(database = get())
    }
}