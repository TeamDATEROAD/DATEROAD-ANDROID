package org.sopt.dateroad.presentation.ui.timelinedetail

import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.usecase.DeleteDateUseCase
import org.sopt.dateroad.domain.usecase.GetDateDetailUseCase
import org.sopt.dateroad.domain.usecase.GetUserIdUseCase
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class TimelineDetailViewModel @Inject constructor(
    private val deleteDateUseCase: DeleteDateUseCase,
    private val getDateDetailUseCase: GetDateDetailUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : BaseViewModel<TimelineDetailContract.TimelineDetailUiState, TimelineDetailContract.TimelineDetailSideEffect, TimelineDetailContract.TimelineDetailEvent>() {
    override fun createInitialState(): TimelineDetailContract.TimelineDetailUiState = TimelineDetailContract.TimelineDetailUiState()

    override suspend fun handleEvent(event: TimelineDetailContract.TimelineDetailEvent) {
        when (event) {
            is TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail -> setState { copy(loadState = event.loadState, dateDetail = event.dateDetail) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteBottomSheet -> setState { copy(showDeleteBottomSheet = event.showDeleteBottomSheet) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowDeleteDialog -> setState { copy(showDeleteDialog = event.showDeleteDialog) }
            is TimelineDetailContract.TimelineDetailEvent.SetShowKakaoDialog -> setState { copy(showKakaoDialog = event.showKakaoDialog) }
            is TimelineDetailContract.TimelineDetailEvent.DeleteDate -> setState { copy(deleteLoadState = event.deleteLoadState) }
            is TimelineDetailContract.TimelineDetailEvent.SetLoadState -> setState { copy(loadState = event.loadState) }
            is TimelineDetailContract.TimelineDetailEvent.SetSideEffect -> setSideEffect(event.sideEffect)
            is TimelineDetailContract.TimelineDetailEvent.ShareKakao -> shareKakao(event.context, event.dateDetail)
        }
    }

    fun fetchDateDetail(dateId: Int) {
        viewModelScope.launch {
            setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState = LoadState.Loading, dateDetail = currentState.dateDetail))
            getDateDetailUseCase(dateId).onSuccess { dateDetail ->
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState = LoadState.Success, dateDetail = dateDetail))
            }.onFailure {
                setEvent(TimelineDetailContract.TimelineDetailEvent.SetTimelineDetail(loadState = LoadState.Error, dateDetail = currentState.dateDetail))
            }
        }
    }

    fun deleteDate(dateId: Int) {
        viewModelScope.launch {
            setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(deleteLoadState = LoadState.Loading))
            deleteDateUseCase(dateId).onSuccess {
                setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(deleteLoadState = LoadState.Success))
            }.onFailure {
                setEvent(TimelineDetailContract.TimelineDetailEvent.DeleteDate(deleteLoadState = LoadState.Error))
            }
        }
    }

    fun shareKakao(context: Context, dateDetail: DateDetail) {
        val templateId = 109999
        val templateArgs = mutableMapOf<String, String>()

        templateArgs["userName"] = getUserIdUseCase()
        templateArgs["startAt"] = dateDetail.startAt

        dateDetail.places.forEachIndexed { index, place ->
            if (index < 5) {
                templateArgs["name${index + 1}"] = place.title
                templateArgs["duration${index + 1}"] = place.duration
            }
        }

        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            ShareClient.instance.shareCustom(context, templateId.toLong(), templateArgs) { sharingResult, error ->
                if (sharingResult != null) {
                    context.startActivity(sharingResult.intent)
                    Log.w("KakaoShare", "Warning Msg: ${sharingResult.warningMsg}")
                    Log.w("KakaoShare", "Argument Msg: ${sharingResult.argumentMsg}")
                }
            }
        } else {
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
