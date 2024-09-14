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

object TimelineDetailAmplitude {
    const val VIEW_SCHEDULE_DETAILS = "view_schedule_details"
    const val CLICK_KAKAO_SHARE = "click_kakao_share"
    const val CLICK_CLOSE_KAKAO = "click_close_kakao"
    const val CLICK_OPEN_KAKAO = "click_open_kakao"
    const val VIEW_PATH = "view_path"
    const val DATE_COURSE_NUM="date_course_num"
    const val DATE_TOTAL_DURATION="date_total_duration"


}
