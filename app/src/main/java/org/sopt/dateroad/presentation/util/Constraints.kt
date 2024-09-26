package org.sopt.dateroad.presentation.util

object CourseDetail {
    const val POINT_LACK = 50
}

object DatePicker {
    const val DATE_PATTERN = "yyyy.MM.dd"
    const val YEAR_START = 2000
    const val YEAR_END = 2024
    const val YEAR_START_INDEX = 24
    const val MONTH_START = 1
    const val MONTH_END = 12
    const val DAY_START = 1
    const val DAY_END = 31
    const val SEPARATOR = "."
}

object Default {
    const val REGION = "지역"
    const val DRAGGED_DISTANCE = 0f
    const val CLICKABLE_DELAY = 500L
}

object DurationPicker {
    const val DURATION_START = 1
    const val DURATION_END = 12
}

object EnrollAmplitude {
    const val VIEW_ADD_SCHEDULE = "view_add_schedule"
    const val CLICK_SCHEDULE1_BACK = "click_schedule1_back"
    const val VIEW_ADD_SCHEDULE2 = "view_add_schedule2"
    const val CLICK_SCHEDULE2_BACK = "click_schedule2_back"
    const val CLICK_BRING_COURSE = "click_bring_course"
    const val VIEW_ADD_BRING_COURSE = "view_add_bringcourse"
    const val VIEW_ADD_BRING_COURSE2 = "view_add_bringcourse2"
    const val VIEW_COURSE1 = "view_course1"
    const val CLICK_COURSE1_BACK = "click_course1_back"
    const val CLICK_COURSE2_BACK = "click_course2_back"
    const val CLICK_COURSE3_BACK = "click_course3_back"
    const val VIEW_PATH = "view_path"
    const val DATE_TITLE = "date_title"
    const val DATE_DATE = "date_date"
    const val DATE_TIME = "date_time"
    const val DATE_TAG_NUM = "date_tag_num"
    const val DATE_AREA = "date_area"
    const val DATE_DETAIL_LOCATION = "date_detail_location"
    const val DATE_DETAIL_TIME = "date_detail_time"
    const val DATE_COURSE_NUM = "date_course_num"
    const val COURSE_IMAGE = "course_image"
    const val COURSE_TITLE = "course_title"
    const val COURSE_DATE = "course_date"
    const val COURSE_START_TIME = "course_start_time"
    const val COURSE_TAGS = "course_tags"
    const val COURSE_LOCATION = "course_location"
    const val DATE_LOCATION = "date_location"
    const val DATE_SPEND_TIME = "date_spend_time"
    const val LOCATION_NUM = "location_num"
    const val COURSE_CONTENT_BOOL = "course_content_bool"
    const val COURSE_CONTENT_NUM = "course_content_num"
    const val COURSE_COST = "course_cost"
}

object EnrollScreen {
    const val FIRST = 1
    const val SECOND = 2
    const val THIRD = 3
    const val MAX_ITEMS = 10
    const val TITLE_MIN_LENGTH = 5
}

object GalleryLauncher {
    const val INPUT = "image/*"
    const val DELETE_IMAGE = ""
}

object Home {
    const val ADVERTISEMENT_DELAY = 4000L
}

object LoadingView {
    const val LOTTIE = "loading.json"
    const val CLIP_MIN = 0
    const val CLIP_MAX = 1200
}

object MyCourseAmplitude {
    const val VIEW_PURCHASED_COURSE = "view_purchased_course"
    const val CLICK_PURCHASED_BACK = "click_purchased_back"
}

object Onboarding {
    const val FIRST = 0
    val FIRST_PAGE_KEYWORD = listOf("포인트", "데이트 코스", "100", "다양한")
    val KEYWORD = listOf("100 포인트", "다양한")
}

object Pattern {
    private const val NICKNAME_PATTERN = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$"
    val NICKNAME_REGEX = Regex(NICKNAME_PATTERN)
}

object Point {
    const val POINT = 50
    const val POINT_USED = "POINT_USED"
    const val POINT_USED_DESCRIPTION = "코스 열람하기"
}

object PointHistoryTab {
    const val GAINED_HISTORY_POSITION = 0
    const val USED_HISTORY_POSITION = 1
}

object ShareKakao {
    const val TEMPLATE_ID = 109999L
    const val USER_NAME = "userName"
    const val START_AT = "startAt"
}

object SignIn {
    const val PLATFORM = "KAKAO"
}

object Time {
    const val TIME = " 시간"
}

object TimelineAmplitude {
    const val VIEW_DATE_SCHEDULE = "view_date_schedule"
    const val COUNT_DATE_SCHEDULE = "count_date_schedule"
    const val CLICK_ADD_SCHEDULE = "click_add_schedule"
    const val DATE_SCHEDULE_NUM = "date_schedule_num"
}

object TimePicker {
    const val AM = "오전"
    const val PM = "오후"
    const val AM_ENG = "AM"
    const val PM_ENG = "PM"
    const val HOUR_START = 1
    const val HOUR_END = 12
    const val MINUTE_START = 0
    const val MINUTE_END = 59
}

object Token {
    const val BEARER = "Bearer "
}

object UserPropertyAmplitude {
    const val USER_NAME = "user_name"
    const val USER_POINT = "user_point"
    const val USER_FREE_REMAINED = "user_free_remained"
    const val USER_PURCHASE_COUNT = "user_purchase_count"
    const val USER_COURSE_COUNT = "user_course_count"
    const val DATE_SCHEDULE_NUM = "date_schedule_num"
}

object ViewPath {
    const val HOME = "홈"
    const val TIMELINE = "데이트 일정"
    const val MY_COURSE_READ = "내가 열람한 코스"
    const val COURSE_DETAIL = "코스 상세"
    const val LOOK = "코스 둘러보기"
}

object WebViewUrl {
    const val PRIVACY_POLICY_URL = "https://www.notion.so/hooooooni/04da4aa279ca4b599193784091a52859"
    const val REPORT_URL = "https://tally.so/r/w4L1a5"
    const val ASK_URL = "https://dateroad.notion.site/1055d2f7bfe94b3fa6c03709448def21?pvs=4"
}
