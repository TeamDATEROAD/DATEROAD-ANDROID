package org.sopt.dateroad.presentation.ui.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.ui.component.numberpicker.DateRoadNumberPicker
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRoadPickerBottomSheet(
    isBottomSheetOpen: Boolean,
    isButtonEnabled: Boolean,
    buttonText: String,
    onButtonClick: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    pickerItems: List<List<String>>
) {
    DateRoadBottomSheet(
        modifier = Modifier.padding(top = 20.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        sheetState = rememberModalBottomSheetState(),
        isBottomSheetOpen = isBottomSheetOpen,
        isButtonEnabled = isButtonEnabled,
        buttonText = buttonText,
        onButtonClick = onButtonClick,
        onDismissRequest = onDismissRequest
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                pickerItems.forEachIndexed { index, item ->
                    DateRoadNumberPicker(
                        modifier = Modifier
                            .weight(1f),
                        items = item
                    )
                    if (index != pickerItems.size - 1) {
                        Spacer(modifier = Modifier.width(17.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(19.dp))
        }
    }
}

@Preview
@Composable
fun DateRoadPickerBottomSheetPreview() {
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }

    Button(onClick = { isBottomSheetOpen = true }) {
        Text(
            text = "DateRoadPickerBottomSheet",
            color = DateRoadTheme.colors.black,
            style = DateRoadTheme.typography.titleExtra24
        )
    }

    DateRoadPickerBottomSheet(
        isBottomSheetOpen = isBottomSheetOpen,
        isButtonEnabled = true,
        buttonText = "취소",
        pickerItems = listOf(
            (2000..2024).map { it.toString() },
            (1..12).map { it.toString() },
            (1..31).map { it.toString() }
        )
    )
}
