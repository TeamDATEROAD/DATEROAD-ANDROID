package org.sopt.dateroad.presentation.ui.past

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import javax.inject.Inject

@HiltViewModel
class PastViewModel @Inject constructor() : BaseViewModel<PastContract.PastUiState, PastContract.PastSideEffect, PastContract.PastEvent>() {
    override fun createInitialState(): PastContract.PastUiState =
        PastContract.PastUiState()

    override suspend fun handleEvent(event: PastContract.PastEvent) {
        when (event) {
            is PastContract.PastEvent.FetchPastDate -> setState { copy(loadState = event.loadState, dates = event.dates) }
        }
    }

    fun fetchPastDate() {
        setEvent(
            PastContract.PastEvent.FetchPastDate(
                loadState = LoadState.Success,
                dates = listOf(
                    Date(
                        dateId = 0,
                        dDay = "3",
                        title = "성수동 당일치기 데이트가볼까요?\n이정도 어떠신지?",
                        date = "2024년 6월 23일",
                        city = "건대/성수/왕십리",
                        tags = listOf(DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 0,
                        dDay = "3",
                        title = "성수동 당일치기 데이트가볼까요?\n이정도 어떠신지?",
                        date = "2024년 6월 23일",
                        city = "건대/성수/왕십리",
                        tags = listOf(DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 0,
                        dDay = "3",
                        title = "성수동 당일치기 데이트가볼까요?\n이정도 어떠신지?",
                        date = "2024년 6월 23일",
                        city = "건대/성수/왕십리",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 0,
                        dDay = "3",
                        title = "성수동 당일치기 데이트가볼까요?\n이정도 어떠신지?",
                        date = "2024년 6월 23일",
                        city = "건대/성수/왕십리",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.EXHIBITION_POP_UP)
                    ),
                    Date(
                        dateId = 0,
                        dDay = "3",
                        title = "성수동 당일치기 데이트가볼까요?\n이정도 어떠신지?",
                        date = "2024년 6월 23일",
                        city = "건대/성수/왕십리",
                        tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POP_UP)
                    )
                )
            )
        )
    }
}