package org.sopt.teamdateroad.presentation.ui.component.tabbar

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.presentation.util.modifier.noRippleClickable
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

enum class SubComposeID {
    HEIGHT,
    WIDTH,
    TAB,
    INDICATOR,
    DIVIDER
}

data class TabPosition(
    val x: Dp,
    val width: Dp
)

@Composable
fun DateRoadTabBar(
    dividerColor: Color = DateRoadTheme.colors.gray300,
    indicatorColor: Color = DateRoadTheme.colors.black,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 250, easing = FastOutSlowInEasing),
    selectedTabPosition: Int = 0,
    tabItem: @Composable () -> Unit
) {
    SubcomposeLayout(
        modifier =
        Modifier
            .selectableGroup()
    ) { constraints ->
        val maxItemHeight =
            subcompose(SubComposeID.HEIGHT, tabItem)
                .map { it.measure(constraints) }.maxOf { it.height }

        val itemWidth =
            constraints.maxWidth / subcompose(SubComposeID.WIDTH, tabItem)
                .map { it.measure(constraints) }.size

        val tabs =
            subcompose(SubComposeID.TAB, tabItem).map {
                it.measure(Constraints.fixed(itemWidth, maxItemHeight))
            }

        val tabPositions =
            List(tabs.size) { index ->
                TabPosition(x = (itemWidth * index).toDp(), width = itemWidth.toDp())
            }

        layout(constraints.maxWidth, maxItemHeight) {
            subcompose(SubComposeID.DIVIDER) {
                Box(
                    modifier = Modifier
                        .background(color = dividerColor)
                        .height(1.dp)
                )
            }.forEach {
                val height = 1.dp.toPx().toInt()
                it.measure(Constraints.fixed(constraints.maxWidth, height)).placeRelative(0, maxItemHeight - height)
            }

            subcompose(SubComposeID.INDICATOR) {
                Box(
                    modifier = Modifier
                        .tabIndicator(tabPositions[selectedTabPosition], animationSpec)
                        .background(color = indicatorColor)
                )
            }.forEach {
                it.measure(Constraints.fixed(itemWidth, maxItemHeight)).placeRelative(0, 0)
            }

            tabs.forEachIndexed { index, placeable ->
                placeable.placeRelative(itemWidth * index, 0)
            }
        }
    }
}

@Composable
private fun Modifier.tabIndicator(
    tabPosition: TabPosition,
    animationSpec: AnimationSpec<Dp>
): Modifier {
    val animatedTabWidth by animateDpAsState(
        targetValue = tabPosition.width,
        animationSpec = animationSpec,
        label = ""
    )
    val animatedIndicatorOffset by animateDpAsState(
        targetValue = tabPosition.x,
        animationSpec = animationSpec,
        label = ""
    )

    return this
        .fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = animatedIndicatorOffset)
        .width(animatedTabWidth)
        .height(2.dp)
}

@Composable
fun DateRoadTabTitle(
    title: String,
    selected: Boolean,
    selectedTextColor: Color = DateRoadTheme.colors.black,
    unselectedTextColor: Color = DateRoadTheme.colors.gray300,
    textStyle: TextStyle = DateRoadTheme.typography.bodyBold17,
    position: Int,
    padding: PaddingValues = PaddingValues(vertical = 15.dp),
    onClick: (Int) -> Unit = {}
) {
    Text(
        text = title,
        color = if (selected) selectedTextColor else unselectedTextColor,
        style = textStyle,
        modifier =
        Modifier
            .padding(paddingValues = padding)
            .noRippleClickable(
                onClick = { onClick(position) }
            ),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun DateRoadTabBarPreview() {
    DATEROADTheme {
        var selectedTabPosition by remember { mutableStateOf(0) }

        val items = listOf("획득 내역", "사용 내역")

        DateRoadTabBar(
            selectedTabPosition = selectedTabPosition
        ) {
            items.forEachIndexed { index, title ->
                DateRoadTabTitle(
                    title = title,
                    selected = index == selectedTabPosition,
                    position = index
                ) {
                    selectedTabPosition = index
                }
            }
        }
    }
}
