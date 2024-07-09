package org.sopt.dateroad.presentation.ui.enroll.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.ui.component.bottomsheet.DateRoadPickerBottomSheet
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.numberpicker.rememberPickerState
import org.sopt.dateroad.presentation.ui.component.textfield.DateRoadBasicTextField
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollPlaceInsertBar(
    modifier: Modifier = Modifier,
    place: Place,
    onSelectedCourseTimeClick: () -> Unit = {},
    onTitleChange: (String) -> Unit = {},
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
            placeholder = stringResource(id = R.string.enroll_place_insert_bar_enter_place_hint),
            value = place.title,
            onValueChange = onTitleChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .weight(72f)
                .background(color = DateRoadTheme.colors.gray100, shape = RoundedCornerShape(14.dp))
                .padding(vertical = 16.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldHeight = maxOf(textFieldHeight, coordinates.size.height)
                }
                .padding(horizontal = 15.dp)
                .noRippleClickable(onClick = onSelectedCourseTimeClick),
            text = place.duration.ifEmpty { stringResource(id = R.string.enroll_place_insert_bar_select_course_time_hint) },
            color = if (place.duration.isEmpty()) DateRoadTheme.colors.gray300 else DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.bodySemi13,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(8.dp))
        DateRoadImageButton(
            modifier = Modifier
                .size(with(density) { textFieldHeight.toDp() }),
            isEnabled = place.duration.isNotEmpty() && place.title.isNotEmpty(),
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
        var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
        var pickerState = rememberPickerState()

        EnrollPlaceInsertBar(
            place = place,
            onTitleChange = { title ->
                place = place.copy(title = title)
            },
            onSelectedCourseTimeClick = {
                isBottomSheetOpen = true
                place = place.copy(duration = "시간")
            }
        )

        DateRoadPickerBottomSheet(
            isBottomSheetOpen = isBottomSheetOpen,
            isButtonEnabled = true,
            buttonText = "적용하기",
            pickerItems = listOf(
                (1..12).map { (it * 0.5).toString() }
            ),
            onButtonClick = {
                place = place.copy(duration = "0.5시간")
                isBottomSheetOpen = false
            }
        )
    }
}