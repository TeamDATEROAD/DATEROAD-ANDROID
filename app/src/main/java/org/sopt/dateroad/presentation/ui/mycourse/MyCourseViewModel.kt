package org.sopt.dateroad.presentation.ui.mycourse

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class MyCourseViewModel @Inject constructor() : BaseViewModel<MyCourseContract.MyCourseUiState, MyCourseContract.MyCourseSideEffect, MyCourseContract.MyCourseEvent>() {
    override fun createInitialState(): MyCourseContract.MyCourseUiState = MyCourseContract.MyCourseUiState()

    override suspend fun handleEvent(event: MyCourseContract.MyCourseEvent) {
        when (event) {
            is MyCourseContract.MyCourseEvent.FetchMyCourseEnroll -> fetchMyCourseEnroll()
            is MyCourseContract.MyCourseEvent.FetchMyCourseRead -> fetchMyCourseRead()
            is MyCourseContract.MyCourseEvent.SetMyCourseType -> setState { copy(myCourseType = event.myCourseType) }
        }
    }

    private fun fetchMyCourseRead() {
        setState {
            copy(
                loadState = LoadState.Success,
                myCourseType = MyCourseType.READ,
                courses = listOf(
                    Course(
                        courseId = 1,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "99999"
                    ),
                    Course(
                        courseId = 2,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "부천",
                        title = "여기 야키니쿠 꼭 먹으러 가세요.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 3,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 4,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 5,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 6,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 7,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    )

                )
            )
        }
    }

    private fun fetchMyCourseEnroll() {
        setState {
            copy(
                loadState = LoadState.Success,
                myCourseType = MyCourseType.ENROLL,
                courses = listOf(
                    Course(
                        courseId = 1,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "99999"
                    ),
                    Course(
                        courseId = 2,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "부천",
                        title = "여기 야키니쿠 꼭 먹으러 가세요.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 3,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 4,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 5,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 6,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    ),
                    Course(
                        courseId = 7,
                        thumbnail = "https://avatars.githubusercontent.com/u/103172971?v=4",
                        city = "건대/성수/왕십리",
                        title = "여기 야키니쿠 꼭 먹으러 가세요\n하지만 일본에 있습니다.",
                        cost = "10만원 초과",
                        duration = "10시간",
                        like = "999"
                    )

                )
            )
        }
    }
}
