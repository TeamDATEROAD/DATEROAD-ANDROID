package org.sopt.dateroad.presentation.ui.read

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class ReadViewModel @Inject constructor() : BaseViewModel<ReadContract.ReadUiState, ReadContract.ReadSideEffect, ReadContract.ReadEvent>() {
    override fun createInitialState(): ReadContract.ReadUiState =
        ReadContract.ReadUiState()

    override suspend fun handleEvent(event: ReadContract.ReadEvent) {
        when (event) {
            is ReadContract.ReadEvent.FetchMyCourseRead -> setState { copy(loadState = event.loadState, courses = event.courses) }
            is ReadContract.ReadEvent.FetchName -> setState { copy(name = event.name) }
        }
    }

    fun fetchMyCourseRead() {
        setEvent(
            ReadContract.ReadEvent.FetchMyCourseRead(
                loadState = LoadState.Success,
                courses = listOf(
                    Course(
                        id = 1,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 2,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 3,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 4,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 5,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        id = 6,
                        url = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    )
                )
            )
        )
    }

    fun fetchName() {
        setEvent(ReadContract.ReadEvent.FetchName(name = "지현"))
    }
}
