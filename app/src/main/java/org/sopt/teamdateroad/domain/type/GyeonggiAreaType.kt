package org.sopt.teamdateroad.domain.type

import org.sopt.teamdateroad.domain.util.Gyeonggi

enum class GyeonggiAreaType(
    val title: String
) {
    GYEONGGI_ENTIRE(
        title = Gyeonggi.GYEONGGI_ENTIRE
    ),
    SEONGNAM(
        title = Gyeonggi.SEONGNAM
    ),
    SUWON(
        title = Gyeonggi.SUWON
    ),
    GOYANG_PAJU(
        title = Gyeonggi.GOYANG_PAJU
    ),
    GIMPO(
        title = Gyeonggi.GIMPO
    ),
    YONGIN_HWASEONG(
        title = Gyeonggi.YONGIN_HWASEONG
    ),
    ANYANG_GWACHEON(
        title = Gyeonggi.ANYANG_GWACHEON
    ),
    POCHEON_YANGJU(
        title = Gyeonggi.POCHEON_YANGJU
    ),
    NAMYANGJU_UIJEONGBU(
        title = Gyeonggi.NAMYANGJU_UIJEONGBU
    ),
    GWANGJU_ICHEON_YEOJU(
        title = Gyeonggi.GWANGJU_ICHEON_YEOJU
    ),
    GAPYEONG_YANGPYEONG(
        title = Gyeonggi.GAPYEONG_YANGPYEONG
    ),
    GUNPO_UIWANG(
        title = Gyeonggi.GUNPO_UIWANG
    ),
    HANAM_GURI(
        title = Gyeonggi.HANAM_GURI
    ),
    SIHEUNG_GWANGMYEONG(
        title = Gyeonggi.SIHEUNG_GWANGMYEONG
    ),
    BUCHEON_ANSHAN(
        title = Gyeonggi.BUCHEON_ANSHAN
    ),
    DONGDUCHEON_YEONCHEON(
        title = Gyeonggi.DONGDUCHEON_YEONCHEON
    ),
    PYEONGTAEK_OSAN_ANSEONG(
        title = Gyeonggi.PYEONGTAEK_OSAN_ANSEONG
    );

    companion object {
        fun String.toGyeonggiAreaTitle(): String = entries.find { it.name == this }?.title ?: ""
        fun String.toGyeonggiAreaType(): GyeonggiAreaType? = entries.find { it.name == this }
        fun String.fromTitleToGyeonggiAreaType(): GyeonggiAreaType? = entries.find { it.title == this }
    }
}
