package org.sopt.dateroad.presentation.ui.look

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.dateroad.R
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.ChipType
import org.sopt.dateroad.presentation.type.MoneyTagType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadAreaButton
import org.sopt.dateroad.presentation.ui.component.button.DateRoadImageButton
import org.sopt.dateroad.presentation.ui.component.chip.DateRoadTextChip
import org.sopt.dateroad.presentation.ui.component.topbar.DateRoadLeftTitleTopBar
import org.sopt.dateroad.presentation.ui.look.component.LookCourseCard
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun LookRoute(
    padding: PaddingValues
) {
    LookScreen(padding)
}

@Composable
fun LookScreen(
    padding: PaddingValues
) {
    val courses = listOf(
        Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "건대/성수/왕십리",
            title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
            cost = "5만원 이하",
            duration = "10시간",
            like = "999"
        ),
        Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대",
            title = "데로 파이띵 !",
            cost = "10만원 이하",
            duration = "1시간",
            like = "3"
        ),
        Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "건대/성수/왕십리",
            title = "성수동 당일치기 데이트 코스 둘러보러 가실까요?",
            cost = "5만원 이하",
            duration = "10시간",
            like = "999"
        ),
        Course(
            id = 1,
            url = "https://avatars.githubusercontent.com/u/103172971?v=4",
            city = "홍대",
            title = "데로 파이띵 !",
            cost = "10만원 이하",
            duration = "1시간",
            like = "3"
        )
    )

    Column(
        modifier = Modifier
            .background(color = DateRoadTheme.colors.white)
            .padding(padding)
            .fillMaxSize()
    ) {
        DateRoadLeftTitleTopBar(
            title = stringResource(id = R.string.top_bar_title_look),
            buttonContent = {
                DateRoadImageButton(
                    isEnabled = true,
                    onClick = {},
                    cornerRadius = 14.dp,
                    paddingHorizontal = 16.dp,
                    paddingVertical = 9.dp
                )
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            DateRoadAreaButton(textContent = "건대/성수/왕십리")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = R.drawable.ic_all_reset),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(MoneyTagType.entries.size) { index ->
                DateRoadTextChip(
                    textId = MoneyTagType.entries[index].titleRes,
                    chipType = ChipType.MONEY
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(courses.size) { index ->
                LookCourseCard(course = courses[index])
            }
        }
    }
}

@Preview()
@Composable
fun LookScreenPreview() {
    DATEROADTheme {
        LookScreen(padding = PaddingValues(0.dp))
    }
}
