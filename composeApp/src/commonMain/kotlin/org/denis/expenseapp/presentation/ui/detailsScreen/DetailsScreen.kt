package org.denis.expenseapp.presentation.ui.detailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expenseapp.composeapp.generated.resources.Res
import expenseapp.composeapp.generated.resources.add_screen_amount
import expenseapp.composeapp.generated.resources.add_screen_amount_hint
import expenseapp.composeapp.generated.resources.add_screen_category
import expenseapp.composeapp.generated.resources.add_screen_date
import expenseapp.composeapp.generated.resources.add_screen_description
import expenseapp.composeapp.generated.resources.add_screen_description_hint
import expenseapp.composeapp.generated.resources.add_screen_save_button
import expenseapp.composeapp.generated.resources.add_screen_title
import org.denis.expenseapp.presentation.common.VisibleDatePicker
import org.denis.expenseapp.presentation.common.topBars.BackButtonTopBar
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiAction
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseUiState
import org.denis.expenseapp.presentation.model.addScreen.AddExpenseViewModel
import org.denis.expenseapp.presentation.model.addScreen.CategoryUiModel
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsUiState
import org.denis.expenseapp.presentation.model.detailsScreen.DetailsViewModel
import org.denis.expenseapp.presentation.theme.ButtonPrimary
import org.denis.expenseapp.presentation.theme.CurrencyInputField
import org.denis.expenseapp.presentation.theme.MainBodyStyle
import org.denis.expenseapp.presentation.theme.TextEditField
import org.denis.expenseapp.presentation.theme.TextEditTitle
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
                AddScreenBody(
                    uiState = uiState,
                    paddingValues = paddingValues
                )
            }
        )
    }

    @Composable
    private fun AddScreenBody(
        uiState: DetailsUiState,
        paddingValues: PaddingValues
    ) {
        MainBodyStyle(
            paddingValues = paddingValues
        ) {
            when (uiState) {
                is DetailsUiState.Loading -> LoadingState()
                is DetailsUiState.Success -> {}
                is DetailsUiState.Error -> ErrorState()
            }
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