package org.sopt.teamdateroad.presentation.ui.enroll.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.presentation.ui.component.bottomsheet.DateRoadPickerBottomSheet
import org.sopt.teamdateroad.presentation.ui.component.bottomsheet.model.Picker
import org.sopt.teamdateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.teamdateroad.presentation.ui.component.textfield.DateRoadBasicTextField
import org.sopt.teamdateroad.presentation.util.modifier.noRippleClickable
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun EnrollPlaceInsertBar(
    modifier: Modifier = Modifier,
    title: String = "",
    duration: String = "",
    onSelectedCourseTimeClick: () -> Unit = {},
    onTitleChange: (String) -> Unit = {},
    onAddCourseButtonClick: () -> Unit = {}
) {
    var textFieldHeight by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DateRoadBasicTextField(
            modifier = Modifier
                .weight(196f)
                .onGloballyPositioned { coordinates ->
                    textFieldHeight = maxOf(textFieldHeight, coordinates.size.height)
                },
            placeholder = stringResource(id = R.string.enroll_place_insert_bar_enter_place_placeholder),
            value = title,
            onValueChange = onTitleChange
        )
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
            text = duration.ifEmpty { stringResource(id = R.string.enroll_place_insert_bar_select_course_time_placeholder) },
            color = if (duration.isEmpty()) DateRoadTheme.colors.gray300 else DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.bodySemi13,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
        DateRoadImageButton(
            modifier = Modifier
                .size(with(density) { textFieldHeight.toDp() }),
            isEnabled = duration.isNotEmpty() && title.isNotEmpty(),
            cornerRadius = 14.dp,
            paddingHorizontal = 15.dp,
            paddingVertical = 15.dp,
            disabledBackgroundColor = DateRoadTheme.colors.gray100,
            disabledContentColor = DateRoadTheme.colors.gray300,
            onClick = { if (duration.isNotEmpty() && title.isNotEmpty()) onAddCourseButtonClick() else Unit }
        )
    }
}

@Preview
@Composable
fun EnrollPlaceInsertBarPreview() {
    DATEROADTheme {
        var title by remember { mutableStateOf("") }
        var duration by remember { mutableStateOf("") }
        var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
        val pickerItems by remember {
            mutableStateOf(listOf(Picker(items = (1..12).map { (it * 0.5).toString() })))
        }

        EnrollPlaceInsertBar(
            title = title,
            duration = duration,
            onTitleChange = { titleValue ->
                title = titleValue
            },
            onSelectedCourseTimeClick = {
                isBottomSheetOpen = true
            }
        )

        DateRoadPickerBottomSheet(
            isBottomSheetOpen = isBottomSheetOpen,
            isButtonEnabled = true,
            buttonText = "적용하기",
            pickers = pickerItems,
            onButtonClick = {
                duration = pickerItems[0].pickerState.selectedItem
                isBottomSheetOpen = false
            }
        )
    }
}
