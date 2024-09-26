package org.sopt.dateroad.data.dataremote.util

object ApiConstraints {
    const val PROFILE_FORM_DATA_IMAGE = "image"
    const val COURSE_FORM_DATA_IMAGE = "images"
    const val APPLICATION_JSON = "application/json"
    const val HTTPS = "https://"
    const val API = "api"
    const val VERSION = "v1"
    const val COURSES = "courses"
    const val DATE_ACCESS = "date-access"
    const val USERS = "users"
    const val REISSUE = "reissue"
    const val CHECK = "check"
    const val NAME = "name"
    const val COURSE_ID = "courseId"
    const val DATE_ID = "dateId"
    const val LIKES = "likes"
    const val SORT = "sort"
    const val COUNTRY = "country"
    const val CITY = "city"
    const val COST = "cost"
    const val SORT_BY = "sortBy"
    const val POINTS = "points"
    const val COURSE = "course"
    const val TAGS = "tags"
    const val PLACES = "places"
    const val MAIN = "main"
    const val WITHDRAW = "withdraw"
    const val ADVERTISEMENTS = "advertisements"
    const val SIGN_IN = "signin"
    const val SIGN_OUT = "signout"
    const val SIGNUP = "signup"
    const val USER_SIGN_UP_DATA = "userSignUpReq"
    const val TAG = "tag"
    const val ADVERTISEMENT_ID = "advId"
    const val DATES = "dates"
    const val TIME = "time"
    const val NEAREST = "nearest"
    const val IS_DEFAULT_IMAGE = "isDefaultImage"
    const val DELETE_METHOD = "DELETE"
}

object Cost {
    const val COST = "원"
}

object Date {
    const val INPUT_FORMAT = "yyyy.MM.dd"
    const val COURSE_DETAIL_OUTPUT_FORMAT = "yyyy년 M월 d일 방문"
    const val DATE_OUTPUT_FORMAT = "yyyy년 M월 d일"
    const val TIMELINE_OUTPUT_FORMAT = "%s\n%d"
    const val MAIN_DATE_OUTPUT_FORMAT = "%d월 %d일"
    const val D_DAY_OUTPUT_FORMAT = "D-"
    const val D_DAY_DEFAULT_LABEL = "D-Day"
    const val NEAREST_DATE_START_OUTPUT_FORMAT = " 시작"
    const val ADVERTISEMENT_DETAIL_OUTPUT_FORMAT = "yyyy년 M월 d일"
}

object Duration {
    const val DURATION = "시간"
}

object Like {
    const val LIKE_MAX = "999+"
    const val THRESHOLD = 999
}

object Point {
    const val POINT = " P"
    const val GAINED = "+"
    const val USED = "-"
}

object TotalCostZero {
    const val ZERO_COST = "무지출"
}
