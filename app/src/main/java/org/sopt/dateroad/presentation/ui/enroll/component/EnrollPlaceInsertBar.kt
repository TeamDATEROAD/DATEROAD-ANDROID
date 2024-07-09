package org.sopt.dateroad.presentation.ui.enroll.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadBasicTextField
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollPlaceInsertBar(
    modifier: Modifier = Modifier,
    place: Place,
) {
    var textFieldHeight by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DateRoadBasicTextField(
            modifier = Modifier
                .weight(196f)
                .onGloballyPositioned { coordinates ->
                    textFieldHeight = maxOf(textFieldHeight, coordinates.size.height)
                },
            placeholder = stringResource(id = R.string.enroll_place_insert_bar_enter_place_hint)
        )
        Spacer(modifier = Modifier.width(8.dp))
        DateRoadBasicTextField(
            modifier = Modifier
                .weight(72f)
                .onGloballyPositioned { coordinates ->
                    textFieldHeight = maxOf(textFieldHeight, coordinates.size.height)
                },
            placeholder = stringResource(id = R.string.enroll_place_insert_bar_select_course_time_hint)
        )
        Spacer(modifier = Modifier.width(8.dp))
        DateRoadImageButton(
            modifier = Modifier
                .size(with(density) { textFieldHeight.toDp() }),
            cornerRadius = 14.dp,
            paddingHorizontal = 15.dp,
            paddingVertical = 15.dp,
            disabledBackgroundColor = DateRoadTheme.colors.gray100,
            disabledContentColor = DateRoadTheme.colors.gray300,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun EnrollPlaceInsertBarPreview() {
    DATEROADTheme {
        var place by remember { mutableStateOf(Place()) }

        EnrollPlaceInsertBar(place = place)
    }
}