package org.sopt.dateroad.presentation.ui.read

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.EmptyViewType
import org.sopt.dateroad.presentation.type.EnrollType
import org.sopt.dateroad.presentation.ui.component.card.DateRoadCourseCard
import org.sopt.dateroad.presentation.ui.component.partialcolortext.PartialColorText
import org.sopt.dateroad.presentation.ui.component.view.DateRoadEmptyView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.dateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.presentation.util.view.LoadState
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun ReadRoute(
    padding: PaddingValues,
    viewModel: ReadViewModel = hiltViewModel(),
    navigateToEnroll: (EnrollType, Int?) -> Unit,
    navigateToCourseDetail: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.fetchName()
        viewModel.fetchMyCourseRead()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle).collect { readSideEffect ->
            when (readSideEffect) {
                is ReadContract.ReadSideEffect.NavigateToEnroll -> navigateToEnroll(EnrollType.TIMELINE, null)
                is ReadContract.ReadSideEffect.NavigateToCourseDetail -> navigateToCourseDetail(readSideEffect.courseId)
            }
        }
    }

    when (uiState.loadState) {
        LoadState.Idle -> DateRoadIdleView()

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> {
            ReadScreen(
                padding = padding,
                readUiState = uiState,
                navigateToEnroll = { viewModel.setSideEffect(ReadContract.ReadSideEffect.NavigateToEnroll) },
                navigateToCourseDetail = { courseId -> viewModel.setSideEffect(ReadContract.ReadSideEffect.NavigateToCourseDetail(courseId = courseId)) }
            )
        }

        LoadState.Error -> DateRoadErrorView()
    }
}

@Composable
fun ReadScreen(
    padding: PaddingValues,
    readUiState: ReadContract.ReadUiState = ReadContract.ReadUiState(),
    navigateToEnroll: () -> Unit,
    navigateToCourseDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = DateRoadTheme.colors.white)
            .padding(padding)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        if (readUiState.courses.isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.read_title_empty, readUiState.name),
                style = DateRoadTheme.typography.titleExtra24
            )
            DateRoadEmptyView(emptyViewType = EmptyViewType.READ)
        } else {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = PartialColorText(text = stringResource(id = R.string.read_title_normal, readUiState.name, readUiState.courses.size), keywords = listOf(readUiState.courses.size.toString()), color = DateRoadTheme.colors.purple600),
                style = DateRoadTheme.typography.titleExtra24
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .padding(vertical = 7.dp, horizontal = 16.dp)
                    .noRippleClickable(onClick = navigateToEnroll),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.read_suggest_enroll),
                    style = DateRoadTheme.typography.titleBold18,
                    color = DateRoadTheme.colors.black
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.btn_look_button_arrow),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(readUiState.courses) { course ->
                    DateRoadCourseCard(course = course, onClick = { navigateToCourseDetail(course.courseId) })
                }
            }
        }
    }
}

@Preview()
@Composable
fun ReadScreenPreview() {
    DATEROADTheme {
        ReadScreen(padding = PaddingValues(0.dp), navigateToCourseDetail = {}, navigateToEnroll = {})
    }
}
