package org.sopt.dateroad.presentation.ui.enroll

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadTextButton
import org.sopt.dateroad.presentation.ui.component.placecard.DateRoadPlaceCard
import org.sopt.dateroad.presentation.ui.enroll.component.EnrollPlaceInsertBar
import org.sopt.dateroad.presentation.util.Time
import org.sopt.dateroad.presentation.util.draganddrop.rememberDragAndDropListState
import org.sopt.dateroad.presentation.util.mutablelist.move
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@SuppressLint("UnrememberedMutableState", "UnnecessaryComposedModifier")
@Composable
fun EnrollSecondScreen(
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onSelectedPlaceCourseTimeClick: () -> Unit,
    onAddPlaceButtonClick: (Place) -> Unit,
    onPlaceTitleValueChange: (String) -> Unit,
    onPlaceEditButtonClick: (Boolean) -> Unit,
    onPlaceCardDeleteButtonClick: (Int) -> Unit,
    onPlaceCardDragAndDrop: (List<Place>) -> Unit
) {
    val scope = rememberCoroutineScope()
    var overScrollJob by remember { mutableStateOf<Job?>(null) }

    val placeLists = rememberUpdatedState(enrollUiState.enroll.places.toMutableStateList())
    val dragDropListState = rememberDragAndDropListState(onMove = { from, to ->
        placeLists.value.move(from, to)
        onPlaceCardDragAndDrop(placeLists.value.toList())
    })

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.enroll_place_title),
            color = DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.bodyBold17
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.enroll_place_description),
            color = DateRoadTheme.colors.gray400,
            style = DateRoadTheme.typography.bodyMed13
        )
        Spacer(modifier = Modifier.height(13.dp))
        EnrollPlaceInsertBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = enrollUiState.place.title,
            duration = enrollUiState.place.duration,
            onTitleChange = onPlaceTitleValueChange,
            onSelectedCourseTimeClick = onSelectedPlaceCourseTimeClick,
            onAddCourseButtonClick = {
                onAddPlaceButtonClick(Place(title = enrollUiState.place.title, duration = enrollUiState.place.duration + Time.TIME))
            }
        )
        Spacer(modifier = Modifier.height(22.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = DateRoadTheme.colors.gray200
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.enroll_place_guide),
                color = DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.bodyMed13
            )
            DateRoadTextButton(
                textContent = stringResource(id = if (enrollUiState.isPlaceEditable) R.string.edit else R.string.complete),
                textStyle = DateRoadTheme.typography.bodyMed13,
                textColor = if (enrollUiState.isPlaceEditable) DateRoadTheme.colors.gray400 else DateRoadTheme.colors.purple600,
                paddingHorizontal = 18.dp,
                paddingVertical = 6.dp,
                onClick = {
                    onPlaceEditButtonClick(!enrollUiState.isPlaceEditable)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            state = dragDropListState.lazyListState,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDrag = { change, offset ->
                            change.consume()
                            dragDropListState.onDrag(offset = offset)

                            if (overScrollJob?.isActive == true) return@detectDragGesturesAfterLongPress

                            dragDropListState
                                .checkForOverScroll()
                                .takeIf { it != 0f }
                                ?.let {
                                    overScrollJob = scope.launch { dragDropListState.lazyListState.scrollBy(it) }
                                } ?: run { overScrollJob?.cancel() }
                        },
                        onDragStart = { offset -> dragDropListState.onDragStart(offset = offset) },
                        onDragEnd = { dragDropListState.onDragInterrupted() },
                        onDragCancel = { dragDropListState.onDragInterrupted() }
                    )
                },
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(enrollUiState.enroll.places.size) { index ->
                DateRoadPlaceCard(
                    modifier = Modifier
                        .zIndex(if (index == dragDropListState.currentIndexOfDraggedItem) 1f else 0f)
                        .graphicsLayer(
                            scaleX = animateFloatAsState(if (dragDropListState.currentIndexOfDraggedItem == index) 1.1f else 1.0f, label = "").value,
                            scaleY = animateFloatAsState(if (dragDropListState.currentIndexOfDraggedItem == index) 1.1f else 1.0f, label = "").value
                        ),
                    placeCardType = if (enrollUiState.isPlaceEditable) PlaceCardType.COURSE_EDIT else PlaceCardType.COURSE_DELETE,
                    place = enrollUiState.enroll.places[index],
                    onIconClick = { onPlaceCardDeleteButtonClick(index) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun EnrollSecondScreenPreview() {
    DATEROADTheme {
        EnrollSecondScreen(
            onAddPlaceButtonClick = {},
            onSelectedPlaceCourseTimeClick = {},
            onPlaceTitleValueChange = {},
            onPlaceEditButtonClick = {},
            onPlaceCardDeleteButtonClick = {},
            onPlaceCardDragAndDrop = {}
        )
    }
}
