package org.sopt.teamdateroad.presentation.ui.coursedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import org.sopt.teamdateroad.R
import org.sopt.teamdateroad.domain.model.CourseDetail
import org.sopt.teamdateroad.domain.model.Place
import org.sopt.teamdateroad.presentation.type.CourseDetailUnopenedDetailType
import org.sopt.teamdateroad.presentation.type.DateTagType.Companion.getDateTagTypeByName
import org.sopt.teamdateroad.presentation.type.EnrollType
import org.sopt.teamdateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.teamdateroad.presentation.ui.component.bottomsheet.DateRoadBasicBottomSheet
import org.sopt.teamdateroad.presentation.ui.component.dialog.DateRoadTwoButtonDialogWithDescription
import org.sopt.teamdateroad.presentation.ui.component.pager.DateRoadImagePager
import org.sopt.teamdateroad.presentation.ui.component.topbar.DateRoadScrollResponsiveTopBar
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadErrorView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadIdleView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadLoadingView
import org.sopt.teamdateroad.presentation.ui.component.view.DateRoadWebView
import org.sopt.teamdateroad.presentation.ui.coursedetail.component.CourseDetailBasicInfo
import org.sopt.teamdateroad.presentation.ui.coursedetail.component.CourseDetailBottomBar
import org.sopt.teamdateroad.presentation.ui.coursedetail.component.CourseDetailUnopenedDetail
import org.sopt.teamdateroad.presentation.ui.coursedetail.component.courseDetailOpenedDetail
import org.sopt.teamdateroad.presentation.util.CourseDetail.POINT_LACK
import org.sopt.teamdateroad.presentation.util.CourseDetailAmplitude.CLICK_COURSE_BACK
import org.sopt.teamdateroad.presentation.util.CourseDetailAmplitude.CLICK_COURSE_PURCHASE
import org.sopt.teamdateroad.presentation.util.CourseDetailAmplitude.COURSE_LIST_ID
import org.sopt.teamdateroad.presentation.util.CourseDetailAmplitude.COURSE_LIST_TITLE
import org.sopt.teamdateroad.presentation.util.CourseDetailAmplitude.PURCHASE_SUCCESS
import org.sopt.teamdateroad.presentation.util.CourseDetailAmplitude.VIEW_COURSE_DETAILS
import org.sopt.teamdateroad.presentation.util.ViewPath.COURSE_DETAIL
import org.sopt.teamdateroad.presentation.util.WebViewUrl.REPORT_URL
import org.sopt.teamdateroad.presentation.util.amplitude.AmplitudeUtils
import org.sopt.teamdateroad.presentation.util.view.LoadState
import org.sopt.teamdateroad.ui.theme.DATEROADTheme
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun CourseDetailRoute(
    viewModel: CourseDetailViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToEnroll: (EnrollType, String, Int?) -> Unit,
    courseId: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { courseDetailSideEffect ->
                when (courseDetailSideEffect) {
                    is CourseDetailContract.CourseDetailSideEffect.NavigateToEnroll -> navigateToEnroll(courseDetailSideEffect.enrollType, courseDetailSideEffect.viewPath, courseDetailSideEffect.id)
                    is CourseDetailContract.CourseDetailSideEffect.PopBackStack -> popBackStack()
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchCourseDetail(courseId = courseId)
    }

    LaunchedEffect(uiState.loadState, lifecycleOwner) {
        if (uiState.loadState == LoadState.Success) {
            with(uiState.courseDetail) {
                AmplitudeUtils.trackEventWithProperties(
                    eventName = VIEW_COURSE_DETAILS,
                    mapOf(
                        COURSE_LIST_ID to courseId,
                        COURSE_LIST_TITLE to title
                    )
                )
            }
        }
    }

    when (uiState.loadState) {
        LoadState.Idle -> DateRoadIdleView()

        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> {
            CourseDetailScreen(
                courseDetailUiState = uiState,
                onDialogPointLack = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogPointLack) },
                onDialogPointLackConfirm = {
                    viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogPointLack)
                    viewModel.setSideEffect(CourseDetailContract.CourseDetailSideEffect.NavigateToEnroll(enrollType = EnrollType.COURSE, viewPath = COURSE_DETAIL, id = null))
                },
                dismissDialogPointLack = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogPointLack) },
                onDialogLookedForFree = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogLookedForFree) },
                dismissDialogLookedForFree = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogLookedForFree) },
                onDialogLookedByPoint = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogLookedByPoint) },
                onDialogDeleteCourse = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogDeleteCourse) },
                onDialogReportCourse = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDialogReportCourse) },
                dismissDialogDeleteCourse = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogDeleteCourse) },
                dismissDialogReportCourse = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogReportCourse) },
                dismissDialogLookedByPoint = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDialogLookedByPoint) },
                onLikeButtonClicked = {
                    when (uiState.courseDetail.isUserLiked) {
                        true -> viewModel.deleteCourseLike(courseId = courseId)
                        false -> viewModel.postCourseLike(courseId = courseId)
                    }
                },
                onDeleteButtonClicked = {
                    viewModel.deleteCourse(courseId = courseId)
                },
                onDeleteCourseBottomSheet = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnDeleteCourseBottomSheet) },
                dismissDeleteCourseBottomSheet = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissDeleteCourseBottomSheet) },
                onReportCourseBottomSheet = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnReportCourseBottomSheet) },
                dismissReportCourseBottomSheet = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissReportCourseBottomSheet) },
                enrollSchedule = { viewModel.setSideEffect(CourseDetailContract.CourseDetailSideEffect.NavigateToEnroll(enrollType = EnrollType.TIMELINE, viewPath = COURSE_DETAIL, id = courseId)) },
                onTopBarIconClicked = {
                    viewModel.setSideEffect(CourseDetailContract.CourseDetailSideEffect.PopBackStack)
                    AmplitudeUtils.trackEventWithProperties(
                        eventName = CLICK_COURSE_BACK,
                        properties = mapOf(
                            CLICK_COURSE_PURCHASE to uiState.hasPointReadDialogOpened,
                            PURCHASE_SUCCESS to uiState.courseDetail.isAccess
                        )
                    )
                },
                openCourseDetail = { viewModel.postUsePoint(courseId = courseId) },
                onReportButtonClicked = {
                    viewModel.setEvent(CourseDetailContract.CourseDetailEvent.OnReportWebViewClicked)
                },
                onReportWebViewClose = { viewModel.setEvent(CourseDetailContract.CourseDetailEvent.DismissReportWebView) }
            )
        }

        LoadState.Error -> DateRoadErrorView()
    }

    when (uiState.usePointLoadState) {
        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Error -> DateRoadErrorView()

        else -> Unit
    }

    when (uiState.deleteLoadState) {
        LoadState.Loading -> DateRoadLoadingView()

        LoadState.Success -> popBackStack()

        LoadState.Error -> DateRoadErrorView()

        else -> Unit
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CourseDetailScreen(
    courseDetailUiState: CourseDetailContract.CourseDetailUiState,
    onDialogPointLack: () -> Unit,
    onDialogPointLackConfirm: () -> Unit,
    dismissDialogPointLack: () -> Unit,
    onDialogLookedForFree: () -> Unit,
    dismissDialogLookedForFree: () -> Unit,
    onDialogLookedByPoint: () -> Unit,
    dismissDialogLookedByPoint: () -> Unit,
    onDialogDeleteCourse: () -> Unit,
    dismissDialogDeleteCourse: () -> Unit,
    onDialogReportCourse: () -> Unit,
    dismissDialogReportCourse: () -> Unit,
    onLikeButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    onDeleteCourseBottomSheet: () -> Unit,
    dismissDeleteCourseBottomSheet: () -> Unit,
    onReportCourseBottomSheet: () -> Unit,
    onReportButtonClicked: () -> Unit,
    onReportWebViewClose: () -> Unit,
    dismissReportCourseBottomSheet: () -> Unit,
    enrollSchedule: () -> Unit,
    onTopBarIconClicked: () -> Unit,
    openCourseDetail: () -> Unit
) {
    var imageHeight by remember { mutableIntStateOf(0) }

    val scrollState = rememberLazyListState()
    val isScrollResponsiveDefault by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset < imageHeight
        }
    }

    val isViewable = courseDetailUiState.courseDetail.isAccess || courseDetailUiState.courseDetail.isCourseMine
    val courseDetailUnopenedType = if (courseDetailUiState.courseDetail.free > 0) CourseDetailUnopenedDetailType.FREE else CourseDetailUnopenedDetailType.POINT

    if (courseDetailUiState.isWebViewOpened) {
        DateRoadWebView(url = REPORT_URL, onClose = onReportWebViewClose)
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(DateRoadTheme.colors.white)
            ) {
                with(courseDetailUiState.courseDetail) {
                    item {
                        DateRoadImagePager(
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    imageHeight = coordinates.size.height
                                },
                            pagerState = PagerState(),
                            images = courseDetailUiState.courseDetail.images,
                            userScrollEnabled = isViewable,
                            like = courseDetailUiState.courseDetail.like.toString()
                        )
                    }

                    item {
                        CourseDetailBasicInfo(
                            date = date,
                            title = title,
                            totalTime = totalTime,
                            totalCostTag = totalCostTag,
                            city = city
                        )
                    }

                    when (isViewable) {
                        true -> {
                            courseDetailOpenedDetail(
                                description = description,
                                startAt = startAt,
                                places = places,
                                totalCost = totalCost,
                                tags = tags.mapNotNull { tag -> tag.getDateTagTypeByName() }
                            )
                            if (!isCourseMine) {
                                item {
                                    Spacer(modifier = Modifier.height(86.dp))
                                }
                            }
                        }

                        false -> {
                            item {
                                CourseDetailUnopenedDetail(
                                    text = description,
                                    free = free,
                                    courseDetailUnopenedDetailType = courseDetailUnopenedType,
                                    onButtonClicked = when (courseDetailUnopenedType) {
                                        CourseDetailUnopenedDetailType.FREE -> onDialogLookedForFree
                                        CourseDetailUnopenedDetailType.POINT -> onDialogLookedByPoint
                                    }
                                )
                            }
                        }
                    }
                }
            }

            DateRoadScrollResponsiveTopBar(
                isDefault = isScrollResponsiveDefault,
                onLeftIconClick = onTopBarIconClicked,
                onRightIconClick = if (courseDetailUiState.courseDetail.isCourseMine) onDeleteCourseBottomSheet else onReportCourseBottomSheet,
                rightIconResource = if (isViewable) R.drawable.btn_course_detail_more_white else null
            )

            if (isViewable && !courseDetailUiState.courseDetail.isCourseMine) {
                CourseDetailBottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    isUserLiked = courseDetailUiState.courseDetail.isUserLiked,
                    onLikeButtonClicked = onLikeButtonClicked,
                    onEnrollButtonClicked = enrollSchedule
                )
            }

            if (courseDetailUiState.isPointReadDialogOpen) {
                DateRoadTwoButtonDialogWithDescription(
                    twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.READ_COURSE,
                    onDismissRequest = { dismissDialogLookedByPoint() },
                    onClickConfirm = {
                        dismissDialogLookedByPoint()
                        if (courseDetailUiState.courseDetail.totalPoint < POINT_LACK) {
                            onDialogPointLack()
                        } else {
                            openCourseDetail()
                        }
                    },
                    onClickDismiss = { dismissDialogLookedByPoint() }
                )
            }

            if (courseDetailUiState.isPointLackDialogOpen) {
                DateRoadTwoButtonDialogWithDescription(
                    twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.POINT_LACK,
                    onDismissRequest = dismissDialogPointLack,
                    onClickConfirm = onDialogPointLackConfirm,
                    onClickDismiss = dismissDialogPointLack
                )
            }

            if (courseDetailUiState.isFreeReadDialogOpen) {
                DateRoadTwoButtonDialogWithDescription(
                    twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.FREE_READ,
                    onDismissRequest = { dismissDialogLookedForFree() },
                    onClickConfirm = {
                        dismissDialogLookedForFree()
                        openCourseDetail()
                    },
                    onClickDismiss = { dismissDialogLookedForFree() }
                )
            }

            if (courseDetailUiState.isDeleteCourseDialogOpen) {
                DateRoadTwoButtonDialogWithDescription(
                    twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.DELETE_COURSE,
                    onDismissRequest = { dismissDialogDeleteCourse() },
                    onClickConfirm = {
                        dismissDialogDeleteCourse()
                        onDeleteButtonClicked()
                    },
                    onClickDismiss = { dismissDialogDeleteCourse() }
                )
            }

            if (courseDetailUiState.isReportCourseDialogOpen) {
                DateRoadTwoButtonDialogWithDescription(
                    twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.REPORT_COURSE,
                    onDismissRequest = { dismissDialogReportCourse() },
                    onClickConfirm = {
                        dismissDialogReportCourse()
                        onReportButtonClicked()
                    },
                    onClickDismiss = { dismissDialogReportCourse() }
                )
            }

            DateRoadBasicBottomSheet(
                isBottomSheetOpen = courseDetailUiState.isDeleteCourseBottomSheetOpen,
                title = stringResource(id = R.string.course_detail_bottom_sheet_title),
                isButtonEnabled = false,
                buttonText = stringResource(id = R.string.course_detail_bottom_sheet_delete),
                itemList = listOf(
                    stringResource(id = R.string.course_detail_bottom_sheet_confirm) to {
                        onDialogDeleteCourse()
                    }
                ),
                onDismissRequest = { dismissDeleteCourseBottomSheet() },
                onButtonClick = {
                    dismissDeleteCourseBottomSheet()
                }
            )

            DateRoadBasicBottomSheet(
                isBottomSheetOpen = courseDetailUiState.isReportCourseBottomSheetOpen,
                title = stringResource(id = R.string.course_detail_bottom_sheet_title),
                isButtonEnabled = false,
                buttonText = stringResource(id = R.string.course_detail_bottom_sheet_delete),
                itemList = listOf(
                    stringResource(id = R.string.course_detail_bottom_sheet_report) to {
                        onDialogReportCourse()
                    }
                ),
                onDismissRequest = { dismissReportCourseBottomSheet() },
                onButtonClick = {
                    dismissReportCourseBottomSheet()
                }
            )
        }
    }
}

@Preview
@Composable
fun CourseDetailScreenPreview() {
    DATEROADTheme {
        val dummyCourseDetail = CourseDetailContract.CourseDetailUiState(
            loadState = LoadState.Success,
            courseDetail = CourseDetail(
                courseId = 1,
                title = "Sample Course",
                description = "This is a sample course description.",
                totalTime = "4 hours",
                totalCost = "$100",
                city = "Seoul",
                images = listOf(
                    "https://via.placeholder.com/300",
                    "https://via.placeholder.com/300"
                ),
                tags = listOf("TAG1", "TAG2"),
                places = listOf(
                    Place(
                        title = "Place 1",
                        duration = "1"
                    ),
                    Place(
                        title = "Place 2",
                        duration = "2"
                    )
                ),
                isCourseMine = true,
                isAccess = true,
                isUserLiked = false,
                like = 10,
                free = 2,
                totalPoint = 30
            )
        )

        CourseDetailScreen(
            courseDetailUiState = dummyCourseDetail,
            onDialogPointLack = {},
            dismissDialogPointLack = {},
            onDialogPointLackConfirm = {},
            onDialogLookedForFree = {},
            dismissDialogLookedForFree = {},
            onDialogLookedByPoint = {},
            dismissDialogLookedByPoint = {},
            onLikeButtonClicked = {},
            onDeleteButtonClicked = {},
            onDeleteCourseBottomSheet = {},
            dismissDeleteCourseBottomSheet = {},
            onReportCourseBottomSheet = {},
            dismissReportCourseBottomSheet = {},
            enrollSchedule = {},
            onTopBarIconClicked = {},
            openCourseDetail = {},
            onReportButtonClicked = {},
            onReportWebViewClose = {},
            onDialogDeleteCourse = {},
            onDialogReportCourse = {},
            dismissDialogDeleteCourse = {},
            dismissDialogReportCourse = {}
        )
    }
}