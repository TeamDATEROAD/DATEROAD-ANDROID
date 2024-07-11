package org.sopt.dateroad.presentation.ui.pastdate

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.presentation.type.MyCourseType
import org.sopt.dateroad.presentation.ui.mycourse.MyCourseContract
import org.sopt.dateroad.presentation.util.base.BaseViewModel
import org.sopt.dateroad.presentation.util.view.LoadState

@HiltViewModel
class PastDateViewModel @Inject constructor() : BaseViewModel<PastDateContract.PastDateUiState, PastDateContract.PastDateSideEffect, MyCourseContract.MyCourseEvent>() {
    override fun createInitialState(): PastDateContract.PastDateUiState = PastDateContract.PastDateUiState()
    override suspend fun handleEvent(event: MyCourseContract.MyCourseEvent) {
        TODO("Not yet implemented")
    }

}
