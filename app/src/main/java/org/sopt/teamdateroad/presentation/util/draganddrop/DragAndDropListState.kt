package org.sopt.teamdateroad.presentation.util.draganddrop

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.Job
import org.sopt.teamdateroad.presentation.util.Default.DRAGGED_DISTANCE
import org.sopt.teamdateroad.presentation.util.lazylist.getVisibleItemInfoFor
import org.sopt.teamdateroad.presentation.util.lazylist.offsetEnd

class DragAndDropListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    private var draggedDistance by mutableFloatStateOf(DRAGGED_DISTANCE)
    private var initiallyDraggedElement by mutableStateOf<LazyListItemInfo?>(null)
    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)
    private val initialOffsets: Pair<Int, Int>?
        get() = initiallyDraggedElement?.let {
            Pair(it.offset, it.offsetEnd)
        }

    private val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfoFor(absoluteIndex = it)
        }

    private var overScrollJob by mutableStateOf<Job?>(null)

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo
            .firstOrNull { item ->
                offset.y.toInt() in item.offset..item.offsetEnd
            }?.also {
                currentIndexOfDraggedItem = it.index
                initiallyDraggedElement = it
            }
    }

    fun onDragInterrupted() {
        draggedDistance = 0f
        currentIndexOfDraggedItem = null
        initiallyDraggedElement = null
        overScrollJob?.cancel()
    }

    fun onDrag(offset: Offset) {
        draggedDistance += offset.y

        initialOffsets?.let { (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance

            currentElement?.let { hovered ->
                lazyListState.layoutInfo.visibleItemsInfo
                    .filterNot { item ->
                        item.offsetEnd <= startOffset || item.offset >= endOffset || hovered.index == item.index
                    }
                    .firstOrNull { item ->
                        when {
                            startOffset > hovered.offset -> (endOffset > item.offset + item.size / 2)
                            else -> (startOffset < item.offset + item.size / 2)
                        }
                    }?.also { item ->
                        currentIndexOfDraggedItem?.let { current ->
                            onMove.invoke(current, item.index)
                        }
                        currentIndexOfDraggedItem = item.index
                    }
            }
        }
    }

    fun checkForOverScroll(): Float {
        return initiallyDraggedElement?.let {
            val startOffset = it.offset + draggedDistance
            val endOffset = it.offsetEnd + draggedDistance

            return@let when {
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff -> diff > 0 }

                draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { diff -> diff < 0 }

                else -> null
            }
        } ?: 0f
    }
}

@Composable
fun rememberDragAndDropListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit
): DragAndDropListState = remember { DragAndDropListState(lazyListState = lazyListState, onMove = onMove) }
