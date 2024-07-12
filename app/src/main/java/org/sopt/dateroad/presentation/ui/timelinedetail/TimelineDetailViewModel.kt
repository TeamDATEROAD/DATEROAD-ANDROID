package org.sopt.dateroad.presentation.ui.timelinedetail

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class TimelineDetailViewModel @Inject constructor() : BaseViewModel<TimelineDetailContract.TimelineDetailUiState, TimelineDetailContract.TimelineDetailSideEffect, TimelineDetailContract.TimelineDetailEvent>() {
    override fun createInitialState(): TimelineDetailContract.TimelineDetailUiState = TimelineDetailContract.TimelineDetailUiState()

    override suspend fun handleEvent(event: TimelineDetailContract.TimelineDetailEvent) {
        when (event) {
            is TimelineDetailContract.TimelineDetailEvent.FetchTimelineDetail -> fetchDateDetail(event.dateId)
            is TimelineDetailContract.TimelineDetailEvent.ShowDeleteBottomSheet -> showDeleteBottomSheet()
            is TimelineDetailContract.TimelineDetailEvent.ShowKaKaoModal -> showKaKaoModal()
            is TimelineDetailContract.TimelineDetailEvent.ShowDeleteDialog -> showDeleteDialog()
        }
    }

    private suspend fun fetchDateDetail(dateId: Int) {
        val dateDetail = DateDetail(
            dateId = dateId,
            title = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스",
            startAt = "12:00",
            city = "건대/상수/왕십리",
            dday = "Day",
            tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP),
            date = "2023.12.31",
            places = listOf(
                Place(sequence = 1, title = "현진이집", duration = "2.5"),
                Place(sequence = 2, title = "지현이집", duration = "2.0"),
                Place(sequence = 3, title = "민석이집", duration = "2.0"),
                Place(sequence = 4, title = "제가근데이런데이트코스왜짜야해요?남자친구가짜면 안되나요? 제가 왜 그래야하죠?", duration = "2.0"),
                Place(sequence = 5, title = "지현이집", duration = "2.0"),
                Place(sequence = 6, title = "민석이집", duration = "2.0"),
                Place(sequence = 7, title = "제가근데이런데이트코스왜짜야해요?남자친구가짜면 안되나요? 제가 왜 그래야하죠?", duration = "2.0"),
                Place(sequence = 8, title = "지현이집", duration = "2.0"),
                Place(sequence = 9, title = "민석이집", duration = "2.0"),
                Place(sequence = 10, title = "제가근데이런데이트코스왜짜야해요?남자친구가짜면 안되나요? 제가 왜 그래야하죠?", duration = "2.0")
            )
        )
        setState { copy(loadState = LoadState.Success, dateDetail = dateDetail) }
    }

    private fun showKaKaoModal() {
        setState { copy(showKakaoDialog = true) }
    }

    private fun showDeleteBottomSheet() {
        setState { copy(showDeleteBottomSheet = true) }
    }

    private fun showDeleteDialog() {
        setState { copy(showDeleteDialog = true) }
    }
}
