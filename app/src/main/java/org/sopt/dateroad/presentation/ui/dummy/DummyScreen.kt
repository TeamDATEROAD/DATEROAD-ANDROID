package org.sopt.dateroad.presentation.ui.dummy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.presentation.util.context.showToast
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.MviState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DummyRoute(
    padding: PaddingValues,
    viewModel: DummyViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getDummies()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { dummySideEffect ->
                when (dummySideEffect) {
                    is DummyContract.DummySideEffect.ShowToast -> context.showToast(dummySideEffect.message)
                }
            }
    }

    when (state.dummyUiState) {
        is MviState.Success -> {
            val dummies = (state.dummyUiState as MviState.Success).data
            DummyScreen(
                padding = padding,
                email = dummies[0].email,
                onDummyTvClicked = { viewModel.setEvent(DummyContract.DummyEvent.OnDummyTvClicked) }
            )
        }

        is MviState.Loading -> {
            LoadingScreen()
        }

        else -> Unit
    }
}

@Composable
fun DummyScreen(
    padding: PaddingValues,
    email: String,
    onDummyTvClicked: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .noRippleClickable {
                onDummyTvClicked()
            }
            .padding(padding)
            .fillMaxSize()
    ) {
        Text(
            text = email,
            color = DateRoadTheme.colors.deepPurple,
            style = DateRoadTheme.typography.titleExtra24
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview()
@Composable
fun DummyScreenPreview() {
    DATEROADTheme {
        DummyScreen(padding = PaddingValues(0.dp), email = "dateroad")
    }
}
