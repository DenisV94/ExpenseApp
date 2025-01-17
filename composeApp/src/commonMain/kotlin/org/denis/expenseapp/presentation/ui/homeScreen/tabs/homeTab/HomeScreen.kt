package org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.denis.expenseapp.presentation.common.topBars.TopBarHome
import org.denis.expenseapp.presentation.model.homeScreen.ExpenseUiModel
import org.denis.expenseapp.presentation.model.homeScreen.HomeUiState
import org.denis.expenseapp.presentation.model.homeScreen.HomeViewModel
import org.denis.expenseapp.presentation.theme.ButtonPrimary
import org.denis.expenseapp.presentation.theme.MainBodyStyle
import org.denis.expenseapp.presentation.theme.TextEditTitle
import org.denis.expenseapp.presentation.ui.addScreen.AddScreen
import org.denis.expenseapp.presentation.ui.detailsScreen.DetailsScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

data object HomeScreen : Screen  {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<HomeViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        // Collect UI state
        val uiState = viewModel.uiState.collectAsState().value

        LaunchedEffect(Unit) {
            viewModel.reloadView()
        }

        Scaffold(
            topBar = {
                TopBarHome("Home")
            },
            content = { paddingValues ->
                HomeScreenBody(
                    paddingValues = paddingValues,
                    uiState = uiState,
                    onClick = {
                        navigator.push(AddScreen())
                    },
                    onItemClick = { expense ->
                        navigator.push(DetailsScreen(expense.id))
                    }
                )
            }
        )
    }

    @Composable
    fun HomeScreenBody(
        uiState: HomeUiState,
        onClick: () -> Unit,
        onItemClick: (ExpenseUiModel) -> Unit,
        paddingValues: PaddingValues
    ) {
        MainBodyStyle(
            paddingValues = paddingValues,
            content = {
                Column {
                    ButtonPrimary(
                        text = "Add Expense",
                        onClick = {
                            onClick()
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    when (uiState) {
                        is HomeUiState.Loading -> {}
                        is HomeUiState.Success ->{
                            ExpenseList(
                                expenses = uiState.userExpenses,
                                onItemClick = onItemClick
                            )
                        }
                        is HomeUiState.Error -> ErrorState()
                    }
                }
            }
        )
    }

    @Composable
    fun ExpenseList(expenses: List<ExpenseUiModel>, onItemClick: (ExpenseUiModel) -> Unit) {
        TextEditTitle(text = "My expenses")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(expenses) { expense ->
                ExpenseItem(
                    expense=expense,
                    onClick = onItemClick
                )
            }
        }
    }

    @Composable
    fun ExpenseItem(
        expense: ExpenseUiModel,
        onClick: (ExpenseUiModel) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                .clickable { onClick(expense) }
        ) {
            // Expense Icon
            Icon(
                painter = painterResource(expense.iconResId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Expense Details
            Column(modifier = Modifier.weight(1f)) {

                // Expense Category
                Text(
                    text = stringResource(expense.categoryName),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                // Expense Date
                Text(
                    text = expense.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = expense.amount,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }

    @Composable
    fun ErrorState() {
        Box(modifier = Modifier.fillMaxWidth()) {

        }
    }
}