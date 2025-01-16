package org.denis.expenseapp.presentation.ui.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.add_screen_amount
import expenseapp.composeapp.generated.resources.add_screen_category
import expenseapp.composeapp.generated.resources.add_screen_date
import expenseapp.composeapp.generated.resources.add_screen_description
import expenseapp.composeapp.generated.resources.add_screen_title
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsUiModel
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsUiState
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsViewModel
import org.denis.expenseapp.presentation.theme.BodyTextBold
import org.denis.expenseapp.presentation.theme.MainBodyStyle
import org.denis.expenseapp.presentation.theme.TextEditTitle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

data class DetailsScreen(val expenseId:Long) : Screen {
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
                    title = stringResource(Res.string.add_screen_title),
                    onBackButtonPressed = { navigator.pop() }
                )
            },
            content = { paddingValues ->
                DetailsScreenBody(
                    uiState = uiState,
                    paddingValues = paddingValues
                )
            }
        )
    }

    @Composable
    private fun DetailsScreenBody(
        uiState: DetailsUiState,
        paddingValues: PaddingValues
    ) {
        MainBodyStyle(
            paddingValues = paddingValues
        ) {
            when (uiState) {
                is DetailsUiState.Loading -> LoadingState()
                is DetailsUiState.Success -> ExpenseForm(
                    uiState = uiState.detailsExpense
                )
                is DetailsUiState.Error -> ErrorState()
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
                Image(
                    modifier = Modifier.size(45.dp).padding(end = 16.dp),
                    painter = painterResource(uiState.iconResId),
                    contentDescription = "Image category"
                )

                Column {
                    TextEditTitle(text = stringResource(Res.string.add_screen_category))
                    BodyTextBold(stringResource( uiState.categoryName))
                }
            }

            // Amount Field
            TextEditTitle(text = stringResource(Res.string.add_screen_amount))
            BodyTextBold(uiState.amount)

            // Date Field
            TextEditTitle(text = stringResource(Res.string.add_screen_date))
            BodyTextBold(uiState.date)

            // Description Field
            TextEditTitle(text = stringResource(Res.string.add_screen_description))
            BodyTextBold(uiState.description)
        }
    }

    @Composable
    private fun LoadingState() {
        Box(modifier = Modifier.fillMaxWidth()) {

        }
    }

    @Composable
    private fun ErrorState() {
        Box(modifier = Modifier.fillMaxWidth()) {

        }
    }

}