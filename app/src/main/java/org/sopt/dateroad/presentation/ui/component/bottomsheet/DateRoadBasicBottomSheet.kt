package org.sopt.dateroad.presentation.ui.component.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.util.modifier.noRippleClickable
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRoadBasicBottomSheet(
    isBottomSheetOpen: Boolean,
    onDismissRequest: () -> Unit = {},
    title: String,
    buttonText: String,
    itemList: List<Pair<String, () -> Unit>>
) {
    DateRoadBottomSheet(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
        sheetState = rememberModalBottomSheetState(),
        isBottomSheetOpen = isBottomSheetOpen,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = title,
                color = DateRoadTheme.colors.black,
                style = DateRoadTheme.typography.titleBold18
            )
            Spacer(modifier = Modifier.height(16.dp))
            itemList.forEach { item ->
                DateRoadBasicBottomSheetContent(
                    text = item.first,
                    onClick = {
                        item.second()
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@Composable
fun DateRoadBasicBottomSheetContent(
    text: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .noRippleClickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            color = DateRoadTheme.colors.deepPurple,
            style = DateRoadTheme.typography.bodySemi15
        )
    }
}

@Preview
@Composable
fun DateRoadBasicBottomSheetPreview() {
    DATEROADTheme {
        var text by rememberSaveable { mutableStateOf("BottomSheet") }
        var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }

        Button(onClick = { isBottomSheetOpen = true }) {
            Text(
                text = text,
                color = DateRoadTheme.colors.black,
                style = DateRoadTheme.typography.titleExtra24
            )
        }

        DateRoadBasicBottomSheet(
            isBottomSheetOpen = isBottomSheetOpen,
            onDismissRequest = { isBottomSheetOpen = !isBottomSheetOpen },
            title = "프로필 사진 설정",
            buttonText = "취소",
            itemList = listOf(
                "사진 등록" to { text = "사진 등록" },
                "사진 삭제" to { text = "사진 삭제" }
            )
        )
    }
}