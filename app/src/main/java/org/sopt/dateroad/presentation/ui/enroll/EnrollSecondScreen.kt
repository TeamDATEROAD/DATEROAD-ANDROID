package org.sopt.dateroad.presentation.ui.enroll

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.PlaceCardType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadTextButton
import org.sopt.dateroad.presentation.ui.component.placecard.DateRoadPlaceCard
import org.sopt.dateroad.presentation.ui.enroll.component.EnrollPlaceInsertBar
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollSecondScreen(
    enrollUiState: EnrollContract.EnrollUiState = EnrollContract.EnrollUiState(),
    onSelectedPlaceCourseTimeClick: () -> Unit,
    onAddPlaceButtonClick: (Place) -> Unit,
    onPlaceTitleValueChange: (String) -> Unit,
    onPlaceEditButtonClick: (Boolean) -> Unit,
    onPlaceCardDeleteButtonClick: (Int) -> Unit
) {
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
            title = enrollUiState.placeTitle,
            duration = enrollUiState.placeDuration,
            onTitleChange = onPlaceTitleValueChange,
            onSelectedCourseTimeClick = onSelectedPlaceCourseTimeClick,
            onAddCourseButtonClick = {
                onAddPlaceButtonClick(Place(sequence = enrollUiState.place.size, title = enrollUiState.placeTitle, duration = enrollUiState.placeDuration))
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
                textContent = stringResource(id = if (enrollUiState.isPlaceEditable) R.string.delete else R.string.edit),
                textStyle = DateRoadTheme.typography.bodyMed13,
                textColor = DateRoadTheme.colors.gray400,
                paddingHorizontal = 18.dp,
                paddingVertical = 6.dp,
                onClick = {
                    onPlaceEditButtonClick(!enrollUiState.isPlaceEditable)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(enrollUiState.place.size) { index ->
                DateRoadPlaceCard(
                    placeCardType = if (enrollUiState.isPlaceEditable) PlaceCardType.COURSE_EDIT else PlaceCardType.COURSE_DELETE,
                    place = enrollUiState.place[index],
                    onIconClick = {onPlaceCardDeleteButtonClick(index)}
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
            onPlaceCardDeleteButtonClick = {}
        )
    }
}
