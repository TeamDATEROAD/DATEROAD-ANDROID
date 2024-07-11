package org.sopt.dateroad.presentation.ui.pastdate

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dateroad.presentation.ui.mycourse.MyCourseContract
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PastDateViewModel @Inject constructor() : BaseViewModel<PastDateContract.PastDateUiState, PastDateContract.PastDateSideEffect, PastDateContract.PastDateEvent>() {
    override fun createInitialState(): PastDateContract.PastDateUiState = PastDateContract.PastDateUiState()
    override suspend fun handleEvent(event: PastDateContract.PastDateEvent) {
        TODO("Not yet implemented")
    }
}