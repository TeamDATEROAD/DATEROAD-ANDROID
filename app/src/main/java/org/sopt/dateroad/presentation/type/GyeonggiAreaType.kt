package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class GyeonggiAreaType(
    @StringRes name: Int
) {
    WHOLE(
        name = R.string.gyeonggi_area_whole
    ),
    SEOUNGNAM(
        name = R.string.gyeonggi_area_seongnam
    ),
    SUWON(
        name = R.string.gyeonggi_area_suwon
    ),
    GOYANG_PAJU(
        name = R.string.gyeonggi_area_goyang_paju
    ),
    KIMPO(
        name = R.string.gyeonggi_area_kimpo
    ),
    YOUNGIN_HWASEONG(
        name = R.string.gyeonggi_area_yongin_hwaseong
    ),
    ANYANG_GWACHEON(
        name = R.string.gyeonggi_area_anyang_gwacheon
    ),
    POCHEON_YANGJU(
        name = R.string.gyeonggi_area_pocheon_yangju
    ),
    NAMYANGJU_UIJEONGBU(
        name = R.string.gyeonggi_area_namyangju_uijeongbu
    ),
    GWANGJU_INCHEON_YEOJU(
        name = R.string.gyeonggi_area_gwangju_icheon_yeoju
    ),
    GAPYEONG_YANGPYEONG(
        name = R.string.gyeonggi_area_gapyeong_yangpyeong
    ),
    GUNPO_UIWANG(
        name = R.string.gyeonggi_area_gunpo_uiwang
    ),
    HANAM_GURI(
        name = R.string.gyeonggi_area_yongin_hanam_guri
    ),
    SIHEUNG_GWANGMYEONG(
        name = R.string.gyeonggi_area_siheung_gwangmyeong
    ),
    BUCHEON_ANSAN(
        name = R.string.gyeonggi_area_bucheon_ansan
    ),
    DONGDUCHEON_YEONCHEON(
        name = R.string.gyeonggi_area_dongducheon_yeoncheon
    ),
    PYEONGTEAK_OSAN_ANSEONG(
        name = R.string.gyeonggi_area_pyeongtaek_osan_anseong
    )
}