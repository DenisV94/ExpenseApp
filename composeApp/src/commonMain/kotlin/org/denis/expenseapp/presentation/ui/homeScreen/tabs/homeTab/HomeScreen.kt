package org.denis.expenseapp.presentation.ui.homeScreen.tabs.homeTab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.home_screen_error
import expenseapp.composeapp.generated.resources.home_screen_my_expenses
import expenseapp.composeapp.generated.resources.home_screen_no_expenses
import expenseapp.composeapp.generated.resources.home_screen_title
import org.denis.expenseapp.presentation.common.BodyTextLarge
import org.denis.expenseapp.presentation.common.BodyTextMedium
import org.denis.expenseapp.presentation.common.BodyTextSmall
import org.denis.expenseapp.presentation.common.LoadingState
import org.denis.expenseapp.presentation.common.MainBodyStyle
import org.denis.expenseapp.presentation.common.topBars.TopBarHome
import org.denis.expenseapp.presentation.model.homeScreen.HomeUiModel
import org.denis.expenseapp.presentation.model.homeScreen.HomeUiState
import org.denis.expenseapp.presentation.model.homeScreen.HomeViewModel
import org.denis.expenseapp.presentation.ui.detailsScreen.DetailsScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

data object HomeScreen : Screen {
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
                TopBarHome(stringResource(Res.string.home_screen_title))
            },
            content = { paddingValues ->
                HomeScreenBody(
                    paddingValues = paddingValues,
                    uiState = uiState,
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
        onItemClick: (HomeUiModel) -> Unit,
        paddingValues: PaddingValues
    ) {
        MainBodyStyle(
            paddingValues = paddingValues,
            content = {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    when (uiState) {
                        is HomeUiState.Loading -> LoadingState()
                        is HomeUiState.Success -> {
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
    fun ExpenseList(expenses: List<HomeUiModel>, onItemClick: (HomeUiModel) -> Unit) {
        Box(
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.surface)
        )
        {
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                BodyTextLarge(text = stringResource(Res.string.home_screen_my_expenses))
                Spacer(modifier = Modifier.height(16.dp))
                if (expenses.isEmpty()) {
                    BodyTextMedium(text = stringResource(Res.string.home_screen_no_expenses))
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(expenses) { expense ->
                            ExpenseItem(
                                expense = expense,
                                onClick = onItemClick
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ExpenseItem(
        expense: HomeUiModel,
        onClick: (HomeUiModel) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                .clickable { onClick(expense) },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Expense Icon
            Icon(
                painter = painterResource(expense.iconResId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(46.dp)
                    .padding(end = 16.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Expense Details
            Column(modifier = Modifier.weight(1f)) {

                // Expense Category
                BodyTextMedium(
                    text = stringResource(expense.categoryName)
                )

                // Expense Date
                BodyTextSmall(
                    text = expense.date
                )
            }

            BodyTextMedium(
                text = expense.amount
            )
        }
    }

    @Composable
    fun ErrorState() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BodyTextLarge(
                    text = stringResource(Res.string.home_screen_error),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}