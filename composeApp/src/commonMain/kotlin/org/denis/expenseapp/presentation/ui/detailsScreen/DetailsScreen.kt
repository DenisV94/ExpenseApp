package org.denis.expenseapp.presentation.ui.detailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.add_screen_amount
import expenseapp.composeapp.generated.resources.add_screen_date
import expenseapp.composeapp.generated.resources.add_screen_description
import expenseapp.composeapp.generated.resources.details_screen_amount
import expenseapp.composeapp.generated.resources.details_screen_category
import expenseapp.composeapp.generated.resources.details_screen_date
import expenseapp.composeapp.generated.resources.details_screen_description
import expenseapp.composeapp.generated.resources.details_screen_title
import org.denis.expenseapp.presentation.common.BodyTextLarge
import org.denis.expenseapp.presentation.common.BodyTextMedium
import org.denis.expenseapp.presentation.common.LoadingState
import org.denis.expenseapp.presentation.common.MainBodyStyle
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsUiModel
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsUiState
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

data class DetailsScreen(val expenseId: Long) : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<DetailsViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        DisposableEffect(expenseId) {
            viewModel.selectedExpense(expenseId)
            onDispose { }
        }

        // Collect UI state
        val uiState = viewModel.uiState.collectAsState().value

        Scaffold(
            topBar = {
                BackButtonTopBar(
                    title = stringResource(Res.string.details_screen_title),
                    onBackButtonPressed = { navigator.pop() }
                )
            },
            content = { paddingValues ->
                DetailsScreenBody(
                    uiState = uiState,
                    paddingValues = paddingValues,
                    navigator = navigator
                )
            }
        )
    }

    @Composable
    private fun DetailsScreenBody(
        uiState: DetailsUiState,
        paddingValues: PaddingValues,
        navigator: Navigator
    ) {
        MainBodyStyle(
            paddingValues = paddingValues
        ) {
            when (uiState) {
                is DetailsUiState.Loading -> LoadingState()
                is DetailsUiState.Success -> ExpenseForm(
                    uiState = uiState.detailsExpense
                )
                is DetailsUiState.Error -> navigator.pop()
            }
        }
    }

    @Composable
    private fun ExpenseForm(
        uiState: DetailsUiModel
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Category Field
            Row {
                Icon(
                    modifier = Modifier.size(45.dp).padding(end = 16.dp),
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(uiState.iconResId),
                    contentDescription = "Image category"
                )

                Column {
                    BodyTextLarge(
                        text = stringResource(Res.string.details_screen_category),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    BodyTextMedium(stringResource(uiState.categoryName))
                }
            }

            // Amount Field
            BodyTextLarge(text = stringResource(Res.string.details_screen_amount))
            BodyTextMedium(uiState.amount)

            // Date Field
            BodyTextLarge(text = stringResource(Res.string.details_screen_date))
            BodyTextMedium(uiState.date)

            // Description Field
            BodyTextLarge(text = stringResource(Res.string.details_screen_description))
            BodyTextMedium(uiState.description)
        }
    }

}