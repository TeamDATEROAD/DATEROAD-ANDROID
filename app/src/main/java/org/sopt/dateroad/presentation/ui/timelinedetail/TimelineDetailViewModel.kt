package org.sopt.dateroad.presentation.ui.timelinedetail

import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.Serializable
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.Place
import org.sopt.dateroad.presentation.type.DateTagType
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState
import javax.inject.Inject

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
            is TimelineDetailContract.TimelineDetailEvent.ShareKakao -> shareKakao(event.context, event.dateDetail)
        }
    }

    fun fetchTimelineDetail(dateId: Int) {
        val dateDetail = DateDetail(
            dateId = dateId,
            title = "5년차 장기연애 커플이 보장하는 성수동 당일치기 데이트 코스",
            startAt = "12:00",
            city = "건대/상수/왕십리",
            dDay = "Day",
            tags = listOf(DateTagType.SHOPPING, DateTagType.DRIVE, DateTagType.EXHIBITION_POPUP),
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

    fun shareKakao(context: Context, dateDetail: DateDetail) {
        val templateId = 109999
        val templateArgs = mapOf(
            "userName" to "이현진",
            "name_1" to (dateDetail.places[0].title ?: ""),
            "duration_1" to (dateDetail.places[0].duration ?: ""),
            "name_2" to (dateDetail.places[1].title ?: ""),
            "duration_2" to (dateDetail.places[1].duration ?: ""),
            "name_3" to (dateDetail.places[2].title ?: ""),
            "duration_3" to (dateDetail.places[2].duration ?: ""),
            "name_4" to (dateDetail.places[3].title ?: ""),
            "duration_4" to (dateDetail.places[3].duration ?: ""),
            "name_5" to (dateDetail.places[4].title ?: ""),
            "duration_5" to (dateDetail.places[4].duration ?: ""),
            "startAt" to dateDetail.startAt
        )

        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            // 카카오톡으로 카카오톡 공유 가능
            ShareClient.instance.shareCustom(context, templateId.toLong(), templateArgs) { sharingResult, error ->
                if (error != null) {
                    Log.e("KakaoShare", "카카오톡 공유 실패", error)
                } else if (sharingResult != null) {
                    Log.d("KakaoShare", "카카오톡 공유 성공 ${sharingResult.intent}")
                    context.startActivity(sharingResult.intent)

                    // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Log.w("KakaoShare", "Warning Msg: ${sharingResult.warningMsg}")
                    Log.w("KakaoShare", "Argument Msg: ${sharingResult.argumentMsg}")
                }
            }
        } else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            val sharerUrl = WebSharerClient.instance.makeCustomUrl(templateId.toLong(), templateArgs)
            try {
                KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
            } catch (e: UnsupportedOperationException) {
                try {
                    KakaoCustomTabsClient.open(context, sharerUrl)
                } catch (e: ActivityNotFoundException) {
                    Log.e("KakaoShare", "웹 브라우저가 설치되지 않음", e)
                }
            }
        }
    }
}

