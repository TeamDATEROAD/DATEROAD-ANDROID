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
            is TimelineDetailContract.TimelineDetailEvent.FetchTimelineDetail -> setState { copy(loadState = event.loadState) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet -> setState { copy(showDeleteBottomSheet = event.showDeleteBottomSheet) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog -> setState { copy(showDeleteDialog = event.showDeleteDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog -> setState { copy(showKakaoDialog = event.showKakaoDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetSourceScreen -> setState { copy(sourceScreen = event.sourceScreen) }
        }
    }

    fun fetchTimelineDetail(dateId: Int) {
        val dateDetail = DateDetail(
            dateId = dateId,
            title = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스",
            startAt = "12:00",
            city = "건대/상수/왕십리",
            dDay = "Day",
            tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP),
            date = "2023.12.31",
            places = listOf(
                Place(title = "현진이집", duration = "2.5"),
                Place(title = "지현이집", duration = "2.0"),
                Place(title = "민석이집", duration = "2.0"),
                Place(title = "현진이집", duration = "2.5"),
                Place(title = "지현이집", duration = "2.0"),
                Place(title = "민석이집", duration = "2.0"),
                Place(title = "현진이집", duration = "2.5"),
                Place(title = "지현이집", duration = "2.0"),
                Place(title = "민석이집", duration = "2.0")
            )
        )
        setState { copy(loadState = LoadState.Success, dateDetail = dateDetail) }
    }

    fun setShowDeleteDialog(show: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog(showDeleteDialog = show))
    }

    fun setShowKakaoDialog(show: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog(showKakaoDialog = show))
    }

    fun setSourceScreen(source: Boolean) {
        setEvent(TimelineDetailContract.TimelineDetailEvent.SetSourceScreen(sourceScreen = source))
    }
}
