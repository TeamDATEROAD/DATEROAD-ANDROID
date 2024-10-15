package org.sopt.teamdateroad.presentation.ui.component.numberpicker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.sopt.teamdateroad.presentation.ui.component.numberpicker.state.PickerState
import org.sopt.teamdateroad.presentation.ui.component.numberpicker.state.rememberPickerState
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DateRoadNumberPicker(
    modifier: Modifier = Modifier,
    pickerState: PickerState = rememberPickerState(),
    items: List<String>,
    startIndex: Int = 0,
    visibleItemCount: Int = 3,
    dividerColor: Color = DateRoadTheme.colors.gray400
) {
    var itemHeightPixel by remember { mutableStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixel.toDp() }

    val visibleItemsMiddle = visibleItemCount / 2
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)

    LaunchedEffect(itemHeightPixel) {
        if (itemHeightPixel > 0) scrollState.scrollToItem(startIndex)
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .map { index -> items[index] }
            .distinctUntilChanged()
            .collect { item ->
                pickerState.selectedItem = item
            }
    }

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeightDp * visibleItemCount),
            flingBehavior = flingBehavior,
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(visibleItemsMiddle) {
                Spacer(modifier = Modifier.height(itemHeightDp))
            }
            items(items.size) { index ->
                DateRoadNumberPickerContent(
                    modifier = Modifier
                        .onSizeChanged { intSize: IntSize -> itemHeightPixel = intSize.height },
                    text = items[index],
                    color = if (pickerState.selectedItem == items[index]) DateRoadTheme.colors.black else DateRoadTheme.colors.gray200
                )
            }
            items(visibleItemsMiddle) {
                Spacer(modifier = Modifier.height(itemHeightDp))
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .offset(y = itemHeightDp * visibleItemsMiddle),
            color = dividerColor,
            thickness = 1.dp
        )
        HorizontalDivider(
            modifier = Modifier
                .offset(y = itemHeightDp * (visibleItemsMiddle + 1)),
            color = dividerColor,
            thickness = 1.dp
        )
    }
}

@Composable
fun DateRoadNumberPickerContent(
    modifier: Modifier = Modifier,
    color: Color,
    text: String
) {
    Box(
        modifier = modifier
            .padding(vertical = 13.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = color,
            style = DateRoadTheme.typography.bodySemi15,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun DateRoadNumberPickerPreview() {
    DATEROADTheme {
        DateRoadNumberPicker(
            items = (0..11).map { it.toString() }
        )
    }
}
