package org.sopt.dateroad.presentation.ui.timeline

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class TimelineViewModel @Inject constructor() : BaseViewModel<TimelineContract.TimelineUiState, TimelineContract.TimelineSideEffect, TimelineContract.TimelineEvent>() {
    override fun createInitialState(): TimelineContract.TimelineUiState = TimelineContract.TimelineUiState()

    override suspend fun handleEvent(event: TimelineContract.TimelineEvent) {
        when (event) {
            is TimelineContract.TimelineEvent.FetchTimeline -> fetchTimeline()
            is TimelineContract.TimelineEvent.PageChanged -> setPage(event.page)
            is TimelineContract.TimelineEvent.AddDateCardClicked -> handleAddDateCardClicked()
            is TimelineContract.TimelineEvent.ShowMaxItemsModal -> showMaxItemsModal()
        }
    }

    private fun setPage(page: Int) {
        setState { copy(currentPage = page) }
    }

    private fun fetchTimeline() {
        setState {
            copy(
                loadState = LoadState.Success,
                dates = listOf(
                    Date(
                        dateId = 1,
                        dDay = "1",
                        title = "데이트 일정 1",
                        date = "JUNE.23",
                        city = "서울",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 2,
                        dDay = "2",
                        title = "데이트 일정 2",
                        date = "JUNE.23",
                        city = "부산",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 3,
                        dDay = "2",
                        title = "데이트 일정 2",
                        date = "JUNE.23",
                        city = "부산",
                        tags = listOf(DateTagType.SHOPPING)
                    ),
                    Date(
                        dateId = 4,
                        dDay = "1",
                        title = "데이트 일정 1",
                        date = "JUNE.23",
                        city = "서울",
                        tags = listOf(DateTagType.SHOPPING)
                    ),
                    Date(
                        dateId = 5,
                        dDay = "2",
                        title = "데이트 일정 2",
                        date = "JUNE.23",
                        city = "부산",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.EXHIBITION_POP_UP)
                    )
                )
            )
        }
    }

    private fun handleAddDateCardClicked() {
        if (currentState.dates.size >= 5) {
            setState { copy(showMaxDateCardModal = true) }
        } else {
            setSideEffect { TimelineContract.TimelineSideEffect.NavigateToEnroll }
        }
    }

    private fun showMaxItemsModal() {
        setState { copy(showMaxDateCardModal = true) }
    }
}
