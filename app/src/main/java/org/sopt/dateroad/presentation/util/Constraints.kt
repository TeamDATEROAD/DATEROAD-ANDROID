package org.sopt.dateroad.presentation.util

object PointHistoryTab {
    const val GAINED_HISTORY_POSITION = 0
    const val USED_HISTORY_POSITION = 1
}

object EnrollScreen {
    const val FIRST = 1
    const val SECOND = 2
    const val THIRD = 3
    const val MAX_ITEMS = 10
    const val TITLE_MIN_LENGTH = 5
}

object TimePicker {
    const val AM = "오전"
    const val PM = "오후"
}

object TotalCostZero {
    const val ZERO_COST = "무지출"
}

object DatePicker {
    const val DATE_PATTERN = "yyyy.MM.dd"
}

object WebViewUrl {
    const val PRIVACY_POLICY_URL = "https://www.notion.so/hooooooni/04da4aa279ca4b599193784091a52859"
    const val REPORT_URL = "https://tally.so/r/w4L1a5"
    const val ASK_URL = "https://dateroad.notion.site/1055d2f7bfe94b3fa6c03709448def21?pvs=4"
}

object Default {
    const val REGION = "지역"
}

object Point {
    const val POINT = 50
    const val POINT_USED = "POINT_USED"
    const val POINT_USED_DESCRIPTION = "코스 열람하기"
}

object Token {
    const val BEARER = "Bearer "
}

object Time {
    const val TIME = " 시간"
}
object LoadingView {
    const val LOTTIE = "loading.json"
    const val CLIPMIN = 0
    const val CLIPMAX = 1200
}

object Pattern {
    private const val NICKNAME_PATTERN = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$"
    val NICKNAME_REGEX = Regex(NICKNAME_PATTERN)
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

object ViewPath {
    const val HOME = "홈"
    const val TIMELINE = "데이트 일정"
    const val MY_COURSE_READ = "내가 열람한 코스"
    const val COURSE_DETAIL = "코스 상세"
    const val LOOK = "코스 둘러보기"
}
